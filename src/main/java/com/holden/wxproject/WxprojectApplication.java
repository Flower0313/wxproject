package com.holden.wxproject;

import com.holden.wxproject.service.impl.PicBannerServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
public class WxprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxprojectApplication.class, args);
        System.out.println("======================== wx start ========================");
    }

}
