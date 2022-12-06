package com.holden.wxproject.controller;

import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @ClassName wxproject-TestController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:56 - 周六
 * @Describe
 */
@RestController
@Api(tags = {"API健康检查"})
public class HealthController {
    @GetMapping("/ok")
    public DataResult<Object> getAllPics() {
        return DataResult.ok();
    }

}
