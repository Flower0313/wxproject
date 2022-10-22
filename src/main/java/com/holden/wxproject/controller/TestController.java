package com.holden.wxproject.controller;

import com.holden.wxproject.pojo.PicBanner;
import com.holden.wxproject.service.PicBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName wxproject-TestController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:56 - 周六
 * @Describe
 */
@RestController
public class TestController {
    @Autowired
    private PicBannerService picBannerService;

    @GetMapping("/test")
    public List<PicBanner> getAllPics() {
        List<PicBanner> allPic = picBannerService.findAllPic();
        return allPic;
    }

}
