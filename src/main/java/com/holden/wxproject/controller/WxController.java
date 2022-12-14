package com.holden.wxproject.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.pojo.ImageMessage;
import com.holden.wxproject.pojo.InMessage;
import com.holden.wxproject.pojo.OutputMessage;
import com.holden.wxproject.service.SotckService;
import com.holden.wxproject.util.DataResult;
import com.holden.wxproject.util.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName wxproject-WxController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日15:42 - 周二
 * @Describe
 */
@Slf4j
@Api(tags = {"微信公众号接口"})
@RestController
public class WxController {
    @Value("${wx.AppId}")
    private String AppId;

    @Value("${wx.AppSecret}")
    private String AppSecret;

    public static final String Token = "w654646";

    @Autowired
    private SotckService stockService;

    @SneakyThrows
    @GetMapping("/access_token")
    public String accessToken() {
        String inputLine = null;
        String access_token = null;
        JSONObject jsonObject = null;
        try {
            // 创建URL对象
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppId + "&secret=" + AppSecret);
            // 打开连接
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            // 读取数据

            while ((inputLine = in.readLine()) != null) {
                jsonObject = JSONObject.parseObject(inputLine);
                access_token = jsonObject.getString("access_token");
            }
            // 关闭连接
            in.close();
            return access_token;
        } catch (Exception e) {
            e.printStackTrace();
            return inputLine;
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "Token")
    public String index(String signature, String timestamp, String nonce, String echostr) throws IOException, NoSuchAlgorithmException {
        try {
            // 1. 将token、timestamp、nonce三个参数进行字典序排序

            String[] arr = {timestamp, nonce, Token};
            if (signature != null && nonce != null) {
                Arrays.sort(arr);
            }
            // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
            StringBuilder sb = new StringBuilder();
            for (String temp : arr) {
                sb.append(temp);
            }
            // 这里利用了hutool的加密工具类
            String sha1 = SecureUtil.sha1(sb.toString());
            // 3. 加密后的字符串与signature对比，如果相同则该请求来源于微信，原样返回echostr
            if (sha1.equals(signature)) {
                return echostr;
            }
            // 接入失败
            return null;
        } catch (Exception e) {
            log.error("[class: WxController.java]-[method: index]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Token", produces = "application/xml;charset=UTF-8")
    public Object handlerMessage(@RequestBody InMessage inMessage) {
        try {
            String msgType = inMessage.getMsgType();
            if (!msgType.equals("text")) {
                return "success";
            }
            OutputMessage outputMsg = new OutputMessage();
            XStream xs = SerializeXmlUtil.createXstream();
            xs.processAnnotations(OutputMessage.class);
            outputMsg.setFromUserName(inMessage.getToUserName());
            outputMsg.setToUserName(inMessage.getFromUserName());
            outputMsg.setCreateTime(System.currentTimeMillis() / 1000);
            outputMsg.setMsgType(msgType);
            switch (msgType) {
                case "text":
                    outputMsg.setContent(returnText(inMessage.getContent()));
                    break;
                case "image":
                    ImageMessage images = new ImageMessage();
                    images.setMediaId(new String[]{inMessage.getMediaId()});
                    outputMsg.setImage(images);
                    break;
                case "voice":
                    System.out.println("语音消息");
                    break;
                case "video":
                    System.out.println("视频");
                    break;
                case "news":
                    System.out.println("图文消息");
                    break;
                default:
                    break;
            }
            System.out.println(xs.toXML(outputMsg));
            return xs.toXML(outputMsg);
        } catch (Exception e) {
            log.error("[class: WxController.java]-[method: handlerMessage]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return "";
        }


    }

    public String returnText(String content) {
        if ((content.contains("利好消息") || content.contains("利好") || content.contains("新闻")) && !content.contains("股票")) {
            DataResult<List<Map<String, Object>>> news = stockService.judgeNews();
            List<Map<String, Object>> data = news.getData();
            StringBuilder sb = new StringBuilder();
            sb.append("截止今日9:15一共有").append(data.size()).append("条利好新闻消息：").append("\n");
            int index = 0;
            for (Map<String, Object> datum : data) {
                if (index++ >= 20) {
                    break;
                }
                sb.append(index).append(".").append(datum.get("title")).append("(").append(datum.get("code")).append(")").append("\n");
            }
            sb.append("......由于字数限制只展示前20条,想看更多请访问\nhttps://holden.games/swagger-ui.html");
            return sb.toString();
        }
        ////////////////////////////
        else if (content.contains("股票") || content.contains("gupiao") || content.contains("stock")) {
            DataResult<List<Map<String, Object>>> stock = stockService.getContiniation(3, 1);
            List<Map<String, Object>> data = stock.getData();
            StringBuilder sb = new StringBuilder();
            int index = 0;
            sb.append("以下是连续上涨天数大于等于3天的股票：\n");
            for (Map<String, Object> datum : data) {
                if (index++ >= 20) {
                    break;
                }
                sb.append(index).append(".").append("代码为").append(datum.get("code"))
                        .append("的股票连续上涨了").append(datum.get("times"))
                        .append("次(天)，总涨幅为").append(datum.get("sumrate")).append("%\n");
            }
            sb.append("......由于字数限制只展示前20条,想看更多请访问\nhttps://holden.games/swagger-ui.html");
            return sb.toString();
        }
        ////////////////////////////
        else if (content.contains("融资融券") || content.contains("融资")) {
            DataResult<List<Map<String, Object>>> finance = stockService.getContiniationFinance(3, 1);
            List<Map<String, Object>> data = finance.getData();
            StringBuilder sb = new StringBuilder();
            int index = 0;
            sb.append("以下是连续上涨天数大于等于3天的融资融券：\n");
            for (Map<String, Object> datum : data) {
                if (index++ >= 20) {
                    break;
                }
                sb.append(index).append(".").append("代码为").append(datum.get("code"))
                        .append("的股票连续融了").append(datum.get("times"))
                        .append("次(天)，总融资为").append(datum.get("sumprice")).append("元\n");
            }
            sb.append("......由于字数限制只展示前20条,想看更多请访问\nhttps://holden.games/swagger-ui.html");
            return sb.toString();
        }
        ////////////////////////////
        else if (content.contains(":") || content.contains("：")) {
            DataResult<List<Map<String, Object>>> result = stockService.targetNews(content.substring(1));
            List<Map<String, Object>> data = result.getData();
            StringBuilder sb = new StringBuilder();
            int index = 0;
            sb.append("截止今日9:15一共获得").append(data.size()).append("条关于").append(content.substring(1)).append("的消息:\n");
            for (Map<String, Object> datum : data) {
                if (index++ >= 20) {
                    break;
                }
                sb.append(index).append(".").append(datum.get("title")).append("(").append(datum.get("code")).append(")").append("\n");
            }
            sb.append("......由于字数限制只展示前20条,想看更多请访问\nhttps://holden.games/swagger-ui.html");
            return sb.toString();
        }
        //////////////////////////
        else {
            DataResult<List<Map<String, Object>>> result = stockService.targetStock(content, content);
            List<Map<String, Object>> data = result.getData();
            if (data.size() == 0) {
                String url = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + content;
                return JSONObject.parseObject(HttpUtil.post(url, (String) null)).getString("content");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(content).append("最新的消息如下：\n");
            for (Map<String, Object> datum : data) {
                sb.append("名称：").append(datum.get("name")).append("\n")
                        .append("代码：").append(datum.get("code")).append("\n")
                        .append("当前价：").append(datum.get("current_price")).append("\n")
                        .append("涨跌幅：").append(datum.get("up_down_rate")).append("\n")
                        .append("换手率：").append(datum.get("turnover_rate")).append("\n")
                        .append("主力净流入：").append(datum.get("big")).append("\n")
                        .append("散户净流入：").append(datum.get("small")).append("\n")
                        .append("成交量：").append(datum.get("deal_amount")).append("\n");
            }
            return sb.toString();
        }
    }

    //sha1加密
    public static String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //使用指定的字节来更新我们的摘要
        md.update(b);
        //获取密文  （完成摘要计算）
        byte[] b2 = md.digest();
        //获取计算的长度
        int len = b2.length;
        //16进制字符串
        String str = "0123456789abcdef";
        //把字符串转为字符串数组
        char[] ch = str.toCharArray();
        //创建一个40位长度的字节数组
        char[] chs = new char[len * 2];
        //循环20次
        for (int i = 0, k = 0; i < len; i++) {
            //获取摘要计算后的字节数组中的每个字节
            byte b3 = b2[i];
            // >>>:无符号右移
            // &:按位与
            //0xf:0-15的数字
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }
        //字符数组转为字符串
        return new String(chs);
    }
}

