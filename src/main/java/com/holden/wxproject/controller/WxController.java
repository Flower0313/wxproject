package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.pojo.ImageMessage;
import com.holden.wxproject.pojo.InMessage;
import com.holden.wxproject.pojo.OutputMessage;
import com.holden.wxproject.util.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.dom4j.DocumentException;
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

/**
 * @ClassName wxproject-WxController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日15:42 - 周二
 * @Describe
 */
@Api(tags = {"微信公众号接口"})
@RestController
public class WxController {
    @Value("${wx.AppId}")
    private String AppId;

    @Value("${wx.AppSecret}")
    private String AppSecret;

    public static final String Token = "w654646";

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
    public void index(HttpServletResponse response, HttpServletRequest request) throws IOException, NoSuchAlgorithmException {
        String method = request.getMethod();
        if ("GET".equals(method)) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            String[] str = {Token, timestamp, nonce};
            // 字典排序
            Arrays.sort(str);
            String bigStr = str[0] + str[1] + str[2];
            // SHA1加密
            String digest = sha1(bigStr);
            // 确认请求来至微信
            if (digest.equals(signature)) {
                response.getWriter().print(echostr);
            }
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Token", produces = "application/xml;charset=UTF-8")
    public Object handlerMessage(@RequestBody InMessage inMessage) throws DocumentException, IOException {
        String msgType = inMessage.getMsgType();
        OutputMessage outputMsg = new OutputMessage();
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(OutputMessage.class);
        outputMsg.setFromUserName(inMessage.getToUserName());
        outputMsg.setToUserName(inMessage.getFromUserName());
        outputMsg.setCreateTime(System.currentTimeMillis() / 1000);
        outputMsg.setMsgType(msgType);
        switch (msgType) {
            case "text":
                outputMsg.setContent(inMessage.getContent());
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

    }

    public String returnText(String content) {
        return content;
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

