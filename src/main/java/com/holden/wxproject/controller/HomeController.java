package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.redis.RedisDao;
import com.holden.wxproject.service.HomeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

/**
 * @ClassName wxproject-HomeController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月19日14:20 - 周三
 * @Describe
 */
@Api(tags = {"个人"})
@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private RedisDao redisDao;


    @GetMapping("/")
    public ModelAndView info() throws SQLException {
        return new ModelAndView("index.html");
    }

    @GetMapping("/data_center")
    public ModelAndView data() {
        ModelAndView view = new ModelAndView("data.html");
        try {
            view.addObject("info", homeService.getInfo());
        } catch (Exception e) {
        }
        return view;
    }


    @GetMapping(value = "/image/{url:.+}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("url") String url) throws IOException {
        return homeService.getImage(url);
    }

    @GetMapping("/test")
    public void test(){
        homeService.test();
    }

}
