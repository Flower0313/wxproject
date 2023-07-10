package com.holden.wxproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 啦啦啦
 */
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
