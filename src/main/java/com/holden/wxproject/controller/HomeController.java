package com.holden.wxproject.controller;

import io.swagger.annotations.Api;
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
    @GetMapping("/")
    public ModelAndView info() throws SQLException {
        return new ModelAndView("index.html");
    }
}
