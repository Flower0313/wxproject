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

import java.sql.SQLException;
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


    @ApiOperation(value = "获取单个股票基本面信息")
    @GetMapping("/get-single-stock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = true, defaultValue = "301266"),
            @ApiImplicitParam(name = "dateType", value = "日期类型(0无，1年，2月,3日)", dataType = "Integer", required = false, defaultValue = "3"),
            @ApiImplicitParam(name = "value", value = "日期值", dataType = "String", required = false, defaultValue = "2022-12-26"),
    })
    public DataResult<Object> getSingleStock(@RequestParam("code") String code, @RequestParam("dateType") Integer dateType, @RequestParam("value") String value) {
        try {
            return stockService.getSingleStock(code, dateType, value);
        } catch (SQLException e) {
            return DataResult.fail("getSingleStock出错");
        }
    }


    @ApiOperation(value = "统计指定日期的股票数")
    @GetMapping("/get-up-stock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = true, defaultValue = "2022-08-04"),
    })
    public DataResult<String> getStockNum(@RequestParam("date") String date) {
        return stockService.getStockNum(date);
    }


    @ApiOperation(value = "行业研报")
    @GetMapping("/get-industry-report")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = true, defaultValue = "2022-12-05"),
    })
    public DataResult<List<Map<String, String>>> getIndustryReport(@RequestParam("date") String date) {
        return stockService.getIndustryReport(date);
    }


    @ApiOperation(value = "截止当天连续上涨或下跌的股票")
    @GetMapping("/get-continuation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times", value = "连涨次数", dataType = "Integer", required = false, defaultValue = "3"),
            @ApiImplicitParam(name = "tag", value = "1上涨2下跌", dataType = "Integer", required = false, defaultValue = "1"),
    })
    public DataResult<List<Map<String, Object>>> getContiniation(@RequestParam("times") Integer times, @RequestParam("tag") Integer tag) {
        return stockService.getContiniation(times, tag);
    }


    @ApiOperation(value = "截止当天连续上涨或下跌的融资融券")
    @GetMapping("/get-continuation-finance")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times", value = "连涨次数", dataType = "Integer", required = false, defaultValue = "3"),
            @ApiImplicitParam(name = "tag", value = "1上涨2下跌", dataType = "Integer", required = false, defaultValue = "1"),
    })
    public DataResult<List<Map<String, Object>>> getContiniationFinance(@RequestParam("times") Integer times, @RequestParam("tag") Integer tag) {
        return stockService.getContiniationFinance(times, tag);
    }


    @ApiOperation(value = "分析昨日新闻对今日股价的涨跌")
    @GetMapping("/judge-news")
    public DataResult<List<Map<String, Object>>> judgeNews() {
        return stockService.judgeNews();
    }


    @ApiOperation(value = "获取讨论热度的股")
    @GetMapping("/stock-hot")
    public DataResult<List<Map<String, Object>>> stockHot() {
        return stockService.stockHot();
    }


    @ApiOperation(value = "获取指定日期的沪深主板的股票(普通人可以买入的)")
    @GetMapping("/get-stock-codes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = true, defaultValue = "2022-12-04"),
    })
    public DataResult<List<String>> getStockCode(@RequestParam("date") String date) {
        return stockService.getStockCode(date);
    }


    @ApiOperation(value = "获得k线图")
    @GetMapping("/get-stock-kline")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "代码", dataType = "String", required = true, defaultValue = "000001"),
            @ApiImplicitParam(name = "dateType", value = "日期类型(0无，1年，2月)", dataType = "Integer", required = false, defaultValue = "0"),
            @ApiImplicitParam(name = "value", value = "日期值", dataType = "String", required = false, defaultValue = "2022"),
    })
    public DataResult<Object> getKline(@RequestParam("code") String code, @RequestParam("dateType") Integer dateType, @RequestParam("value") String value) {
        return stockService.getKline(code, dateType, value);
    }


    @ApiOperation(value = "获得股价可执行日期")
    @GetMapping("/get-stock-calendar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dateType", value = "日期类型(0无，1年，2月)", dataType = "Integer", required = false, defaultValue = "1"),
            @ApiImplicitParam(name = "value", value = "日期值(可以传多值，用逗号隔开)", dataType = "String", required = false, defaultValue = "2022"),
    })
    public DataResult<Object> getCalendar(@RequestParam("dateType") Integer dateType, @RequestParam("value") String value) {
        return stockService.getCalendar(dateType, value);
    }


    @ApiOperation(value = "获得每年首次末次执行年月")
    @GetMapping("/get-max-min-ds")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期值(年)", dataType = "String", required = false, defaultValue = "2022"),
    })
    public DataResult<Object> getMaxMinDs(@RequestParam("date") String date) {
        return stockService.getMaxMinDs(date);
    }


    @ApiOperation(value = "根据市值来选择股票")
    @GetMapping("/get-column")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期值", dataType = "String", required = true, defaultValue = "2022-12-30"),
            @ApiImplicitParam(name = "ratio", value = "比例(1-100)%", dataType = "Double", required = false, defaultValue = "16"),
            @ApiImplicitParam(name = "type", value = "基本面字段", dataType = "Integer", required = false, defaultValue = "10002"),
            @ApiImplicitParam(name = "order", value = "排序(desc或asc)", dataType = "String", required = false, defaultValue = "desc"),
    })
    public DataResult<Object> getBigMarketStocks(@RequestParam("date") String date, @RequestParam("ratio") Double ratio, @RequestParam("order") String order, @RequestParam("type") Integer type) {
        return stockService.getBigMarketStocks(date, ratio, order, type);
    }


}
