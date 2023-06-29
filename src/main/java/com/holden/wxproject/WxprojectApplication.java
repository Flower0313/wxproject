package com.holden.wxproject;

import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.pojo.HoldenOptions;
import com.holden.wxproject.controller.MyBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WxprojectApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(WxprojectApplication.class, args);
        System.out.println("======================== wx start ========================");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/img/");
        registry.addResourceHandler("/vendor/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/vendor/");
        super.addResourceHandlers(registry);
    }
}
