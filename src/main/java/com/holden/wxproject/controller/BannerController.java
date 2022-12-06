package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.holden.wxproject.service.PicBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName wxproject-BannerController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日15:45 - 周六
 * @Describe
 */
@Api(tags = {"微信轮播图"})
@RestController
public class BannerController {
    @Autowired
    private PicBannerService picBannerService;

    @ApiOperation(value = "获取单个图片链接")
    @GetMapping("/get-pic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picId", value = "图片序号", dataType = "Long", required = true, defaultValue = "1"),
    })
    public void get(HttpServletResponse resp, @RequestParam("picId") Long picId) throws IOException {
        picBannerService.getPic(resp, picId);
    }

    @ApiOperation(value = "获取所有轮播图url")
    @GetMapping("/getBanners")
    public JSONArray getAllPics() {
        JSONArray allPic = picBannerService.findAllPic();
        return allPic;
    }
}
