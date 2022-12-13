package com.holden.wxproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.service.SotckService;
import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @ClassName wxproject-StockController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日17:32 - 周一
 * @Describe 走clickhouse
 */
@Api(tags = {"股票接口"})
@RestController
public class StockController {
    @Autowired
    private SotckService stockService;



    @SneakyThrows
    @ApiOperation(value = "获取单个股票基本面信息")
    @GetMapping("/get-single-stock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = true, defaultValue = "301266"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = true, defaultValue = "2022-08-04"),
    })
    public DataResult<JSONObject> getSingleStock(@RequestParam("code") String code, @RequestParam("date") String date) {
        return stockService.getSingleStock(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "统计指定日期的股票数")
    @GetMapping("/get-up-stock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = true, defaultValue = "2022-08-04"),
    })
    public DataResult<String> getStockNum(@RequestParam("date") String date) {
        return stockService.getStockNum(date);
    }

    @SneakyThrows
    @ApiOperation(value = "行业研报")
    @GetMapping("/get-industry-report")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = true, defaultValue = "2022-12-05"),
    })
    public DataResult<List<Map<String, String>>> getIndustryReport(@RequestParam("date") String date) {
        return stockService.getIndustryReport(date);
    }

    @SneakyThrows
    @ApiOperation(value = "截止当天连续上涨或下跌的股票")
    @GetMapping("/get-continuation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times", value = "连涨次数", dataType = "Integer", required = false, defaultValue = "3"),
            @ApiImplicitParam(name = "tag", value = "1上涨2下跌", dataType = "Integer", required = false, defaultValue = "1"),
    })
    public DataResult<List<Map<String, Object>>> getContiniation(@RequestParam("times") Integer times, @RequestParam("tag") Integer tag) {
        return stockService.getContiniation(times, tag);
    }

    @SneakyThrows
    @ApiOperation(value = "截止当天连续上涨或下跌的融资融券")
    @GetMapping("/get-continuation-finance")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times", value = "连涨次数", dataType = "Integer", required = false, defaultValue = "3"),
            @ApiImplicitParam(name = "tag", value = "1上涨2下跌", dataType = "Integer", required = false, defaultValue = "1"),
    })
    public DataResult<List<Map<String, Object>>> getContiniationFinance(@RequestParam("times") Integer times, @RequestParam("tag") Integer tag) {
        return stockService.getContiniationFinance(times, tag);
    }

    @SneakyThrows
    @ApiOperation(value = "分析昨日新闻对今日股价的涨跌")
    @GetMapping("/judge-news")
    public DataResult<List<Map<String, Object>>> judgeNews() {
        return stockService.judgeNews();
    }

    @SneakyThrows
    @ApiOperation(value = "获取讨论热度的股")
    @GetMapping("/stock-hot")
    public DataResult<List<Map<String, Object>>> stockHot() {
        return stockService.stockHot();
    }




}
