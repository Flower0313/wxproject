package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.holden.wxproject.pojo.NursingInfo;
import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName wxproject-StockController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日17:32 - 周一
 * @Describe 走clickhouse
 */
@Api(tags = {"股票接口"})
@RestController
public class StockController {
    @ApiOperation(value = "获取单个股票基本面信息")
    @GetMapping("/get-single-stock")
    public DataResult<JSONArray> getSingleStock() throws IOException {
        return null;
    }
}
