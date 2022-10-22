package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.holden.wxproject.service.PicBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName wxproject-BannerController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日15:45 - 周六
 * @Describe
 */
@RestController
public class BannerController {
    @Autowired
    private PicBannerService picBannerService;

    @GetMapping("/get-pic")
    public void get(HttpServletResponse resp, @RequestParam("picId") Long picId) throws IOException {
        picBannerService.getPic(resp, picId);
    }

    @GetMapping("/getBanners")
    public JSONArray getAllPics() {
        JSONArray allPic = picBannerService.findAllPic();
        return allPic;
    }
}
