package com.holden.wxproject.controller;

import com.holden.wxproject.service.HomeService;
import com.holden.wxproject.service.TelegramService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * @ClassName wxproject-TelegramBot
 * @Athor Holden_-__--___Xiao
 * @Create 2023年3月18日14:02 - 周六
 * @Describe
 */
@Api(tags = {"电报机器人"})
@RestController
public class TelegramBotController {

    @Autowired
    private TelegramService telegramService;

    @GetMapping("/add/{id}/{score}")
    public String update(@PathVariable("id") String id, @PathVariable("score") Integer score) throws SQLException {
        return telegramService.add(id, score);
    }

    @GetMapping("/select")
    public String select(){
        return telegramService.select();
    }

}
