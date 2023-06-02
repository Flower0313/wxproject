package com.holden.wxproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName wxproject-SwaggerConfig
 * @Athor Holden_-__--___Xiao
 * @Create 2022年10月23日10:58 - 周日
 * @Describe swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2(@Value("${spring.profiles.active}") String profile) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //生产环境不开启swagger
                .enable(!profile.contains("prod"))
                .apiInfo(new ApiInfoBuilder()
                        .title("华小量化Swagger API文档")
                        .description("by 肖华")
                        .version("1.0")
                        .contact(new Contact("holdenxiao", "https://holdenxiao.cn", "123103003@qq.com"))
                        .build())
                .groupName("1.X版本")
                .select()
                //指定扫描controller的接口
                .apis(RequestHandlerSelectors.basePackage("com.holden.wxproject.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
