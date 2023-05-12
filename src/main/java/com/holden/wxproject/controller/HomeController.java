package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.redis.RedisDao;
import com.holden.wxproject.service.HomeService;
import com.holden.wxproject.service.IndexService;
import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * @ClassName wxproject-HomeController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月19日14:20 - 周三
 * @Describe
 */
@Api(tags = {"个人简历"})
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

}
