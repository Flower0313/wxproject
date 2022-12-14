package com.holden.wxproject.controller;


import com.holden.wxproject.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName wxproject-IndexController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月25日11:54 - 周二
 * @Describe
 */
@Api(tags = {"指标计算"})
@RestController
@RequestMapping(value = "/index/")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @SneakyThrows
    @ApiOperation(value = "MA指标(非递归)")
    @PostMapping("ma")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几天", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
    })
    public Double MA(@RequestParam("day") Integer day, @RequestParam("code") String code) {

        return indexService.MA(code, day);
    }

    @SneakyThrows
    @ApiOperation(value = "BIAS指标(非递归)")
    @PostMapping("bias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几天", dataType = "Integer", required = false, defaultValue = "6"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
    })
    public Double BIAS(@RequestParam("day") Integer day, @RequestParam("code") String code) {
        return indexService.BIAS(code, day);
    }


    @SneakyThrows
    @ApiOperation(value = "WR指标(非递归)")
    @PostMapping("wr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几天", dataType = "Integer", required = false, defaultValue = "6"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
    })
    public Double WR(@RequestParam("day") Integer day, @RequestParam("code") String code) {
        return indexService.WR(code, day);
    }


    @SneakyThrows
    @ApiOperation(value = "BBI指标(非递归)")
    @PostMapping("bbi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
    })
    public Double BBI(@RequestParam("code") String code) {
        return indexService.BBI(code);
    }

    @SneakyThrows
    @ApiOperation(value = "ROC指标(非递归)")
    @PostMapping("roc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "day", value = "几天", dataType = "Integer", required = false, defaultValue = "12"),
    })
    public Double ROC(@RequestParam("code") String code, @RequestParam("day") Integer day) {
        return indexService.ROC(code, day);
    }

    @SneakyThrows
    @ApiOperation(value = "UPPER_ENE指标(非递归)")
    @PostMapping("upper_ene")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "688084"),
            @ApiImplicitParam(name = "day", value = "几天", dataType = "Integer", required = false, defaultValue = "6"),
    })
    public Double UPPER_ENE(@RequestParam("code") String code, @RequestParam("day") Integer day) {
        return indexService.UPPER_ENE(code, day);
    }


    @SneakyThrows
    @ApiOperation(value = "RSI指标")
    @PostMapping("rsi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几天", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
    })
    public Double RSI(@RequestParam("day") Integer day, @RequestParam("code") String code) {
        return indexService.RSI(code, day);
    }


}