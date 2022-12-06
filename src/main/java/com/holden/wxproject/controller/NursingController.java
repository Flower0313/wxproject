package com.holden.wxproject.controller;

import com.holden.wxproject.pojo.NursingInfo;
import com.holden.wxproject.service.NursingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName wxproject-NursingController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日12:06 - 周一
 * @Describe
 */
@Api(tags = {"陪护信息接口"})
@RestController
public class NursingController {
    @Autowired
    private NursingService nursingService;

    @ApiOperation(value = "获取所有陪护信息")
    @GetMapping("/get-nursing")
    public List<NursingInfo> getAllNursing() throws IOException {
        return nursingService.getAllNursing();
    }
}
