package com.holden.wxproject.controller;

import com.holden.wxproject.mapper.PicBannerMapper;
import com.holden.wxproject.service.PicBannerService;
import com.holden.wxproject.service.impl.PicBannerServiceImpl;
import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName wxproject-IndexController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月25日11:54 - 周二
 * @Describe
 */
@Api(tags = {"首页"})
@RestController
public class IndexController {
    @Autowired
    private PicBannerMapper picBannerMapper;

    @Value("${file.storeage}")
    private String FileStoreage;

    @Value("${file.http}")
    private String FileHttp;

    @GetMapping("/")
    public void index(HttpServletResponse resp) throws IOException {
        //获取图片的url名称
        String picUrl = picBannerMapper.getPicUrl(7L);
        FileSystemResource file = new FileSystemResource(FileStoreage + "/" + picUrl);
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        //将文件变成excel格式
        resp.setContentType("image/png");
        try {
            inputStream = file.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(resp.getOutputStream());
            FileCopyUtils.copy(bufferedInputStream, bufferedOutputStream);
        } catch (Exception e) {
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != bufferedInputStream) {
                bufferedInputStream.close();
            }
            if (null != bufferedOutputStream) {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
        }
    }
}

