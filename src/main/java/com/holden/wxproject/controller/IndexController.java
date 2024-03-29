package com.holden.wxproject.controller;


import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.service.IndexService;
import com.holden.wxproject.util.DataResult;
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
@Api(tags = {"指标计算(2008-12-01 到 2022-12-30)"})
@RestController
@RequestMapping(value = "/index/")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @SneakyThrows
    @ApiOperation(value = "MA指标(非递归)")
    @PostMapping("ma")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),

    })
    public Double MA(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("date") String date) {

        return indexService.MA(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "MA指标列表")
    @PostMapping("ma-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> MALIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.MALIST(code, day, year);
    }

    @SneakyThrows
    @ApiOperation(value = "BIAS指标(非递归)")
    @PostMapping("bias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "6"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-15"),
    })
    public Double BIAS(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.BIAS(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "BIAS指标列表")
    @PostMapping("bias-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> BIASLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.BIASLIST(code, day, year);
    }


    @SneakyThrows
    @ApiOperation(value = "WR指标(非递归)")
    @PostMapping("wr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "6"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-15"),
    })
    public Double WR(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.WR(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "WR指标列表")
    @PostMapping("wr-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> WRLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.WRLIST(code, day, year);
    }


    @SneakyThrows
    @ApiOperation(value = "BBI指标(非递归)")
    @PostMapping("bbi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-15"),
    })
    public Double BBI(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.BBI(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "BBI指标列表")
    @PostMapping("bbi-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> BBILIST(@RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.BBILIST(code, year);
    }

    @SneakyThrows
    @ApiOperation(value = "ROC指标(非递归)")
    @PostMapping("roc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "12"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-15"),
    })
    public Double ROC(@RequestParam("code") String code, @RequestParam("day") Integer day, @RequestParam("date") String date) {
        return indexService.ROC(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "ROC指标列表")
    @PostMapping("roc-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> ROCLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.ROCLIST(code, day, year);
    }

    @SneakyThrows
    @ApiOperation(value = "ENE指标(非递归)")
    @PostMapping("ene")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "688084"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "25"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),
    })
    public JSONObject ENE(@RequestParam("code") String code, @RequestParam("day") Integer day, @RequestParam("date") String date) {
        return indexService.ENE(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "ENE指标列表")
    @PostMapping("ene-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> ENELIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.ENELIST(code, day, year);
    }


    @SneakyThrows
    @ApiOperation(value = "PSY指标(非递归)")
    @PostMapping("psy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "688084"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "12"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),
    })
    public Double PSY(@RequestParam("code") String code, @RequestParam("day") Integer day, @RequestParam("date") String date) {
        return indexService.PSY(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "PSY指标列表")
    @PostMapping("psy-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> PSYLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.PSYLIST(code, day, year);
    }


    @SneakyThrows
    @ApiOperation(value = "BRAR指标(非递归)")
    @PostMapping("brar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "26"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),
    })
    public JSONObject BRAR(@RequestParam("code") String code, @RequestParam("day") Integer day, @RequestParam("date") String date) {
        return indexService.BRAR(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "BRAR指标列表")
    @PostMapping("brar-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> BRARLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.BRARLIST(code, day, year);
    }

    @SneakyThrows
    @ApiOperation(value = "ATR指标(非递归)")
    @PostMapping("atr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "14"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),

    })
    public Double ATR(@RequestParam("code") String code, @RequestParam("day") Integer day, @RequestParam("date") String date) {
        return indexService.ATR(code, day, date);
    }

    @SneakyThrows
    @ApiOperation(value = "ATR指标列表")
    @PostMapping("atr-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> ATRLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.ATRLIST(code, day, year);
    }

    @SneakyThrows
    @ApiOperation(value = "EMV指标(非递归)")
    @PostMapping("emv")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "000001"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-30"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "14"),
            @ApiImplicitParam(name = "circle", value = "周期内移动平均", dataType = "Integer", required = false, defaultValue = "9"),
    })
    public Double EMV(@RequestParam("code") String code, @RequestParam("date") String date, @RequestParam("day") Integer day, @RequestParam("circle") Integer circle) {
        return indexService.EMV(code, date, day,circle);
    }

    @SneakyThrows
    @ApiOperation(value = "EMV指标列表")
    @PostMapping("emv-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "14"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "000002"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),
            @ApiImplicitParam(name = "circle", value = "周期内移动平均", dataType = "Integer", required = false, defaultValue = "9"),


    })
    public DataResult<Object> EMVLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year, @RequestParam("circle") Integer circle) {
        return indexService.EMVLIST(code, day, year,circle);
    }

    @SneakyThrows
    @ApiOperation(value = "DPO指标(非递归)")
    @PostMapping("dpo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "circle", value = "周期内移动平均", dataType = "Integer", required = false, defaultValue = "10"),
    })
    public Double DPO(@RequestParam("code") String code, @RequestParam("date") String date, @RequestParam("day") Integer day, @RequestParam("circle") Integer circle) {
        return indexService.DPO(code, date, day, circle);
    }

    @SneakyThrows
    @ApiOperation(value = "DPO指标列表")
    @PostMapping("dpo-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),
            @ApiImplicitParam(name = "circle", value = "周期内移动平均", dataType = "Integer", required = false, defaultValue = "10"),
    })
    public DataResult<Object> DPOLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year, @RequestParam("circle") Integer circle) {
        return indexService.DPOLIST(code, day, year, circle);
    }

    @SneakyThrows
    @ApiOperation(value = "MTM指标(非递归)")
    @PostMapping("mtm")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-14"),
            @ApiImplicitParam(name = "circle", value = "周期内移动平均", dataType = "Integer", required = false, defaultValue = "12"),

    })
    public Double MTM(@RequestParam("code") String code, @RequestParam("date") String date, @RequestParam("circle") Integer circle) {
        return indexService.MTM(code, date, circle);
    }

    @SneakyThrows
    @ApiOperation(value = "MTM指标列表")
    @PostMapping("mtm-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> MTMLIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.MTMLIST(code, day, year);
    }


    @SneakyThrows
    @ApiOperation(value = "ASI指标(非递归)")
    @PostMapping("asi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-15"),
            @ApiImplicitParam(name = "day", value = "周期内移动平均", dataType = "Integer", required = false, defaultValue = "26"),

    })
    public Double ASI(@RequestParam("code") String code, @RequestParam("date") String date, @RequestParam("day") Integer day) {
        return indexService.ASI(code, date, day);
    }

    @SneakyThrows
    @ApiOperation(value = "ASI指标列表")
    @PostMapping("asi-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "day", value = "几日移动平均", dataType = "Integer", required = false, defaultValue = "5"),
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> ASILIST(@RequestParam("day") Integer day, @RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.ASILIST(code, day, year);
    }

    @SneakyThrows
    @ApiOperation(value = "KDJ指标(递归)")
    @PostMapping("kdj")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public JSONObject KDJ(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.KDJ(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "KDJ指标列表")
    @PostMapping("kdj-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> KDJLIST(@RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.KDJLIST(code, year);
    }


    @SneakyThrows
    @ApiOperation(value = "MACD指标(递归)")
    @PostMapping("macd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public JSONObject MACD(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.MACD(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "MACD指标列表")
    @PostMapping("macd-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "002336"),
            @ApiImplicitParam(name = "year", value = "年", dataType = "String", required = false, defaultValue = "2022"),

    })
    public DataResult<Object> MACDLIST(@RequestParam("code") String code, @RequestParam("year") String year) {
        return indexService.MACDLIST(code, year);
    }


    @SneakyThrows
    @ApiOperation(value = "SAR指标(递归)")
    @PostMapping("sar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public Double SAR(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.SAR(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "DMI指标(递归)")
    @PostMapping("dmi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public JSONObject DMI(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.DMI(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "MIKE指标(递归)")
    @PostMapping("mike")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public JSONObject MIKE(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.MIKE(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "RSI指标(递归)")
    @PostMapping("rsi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public JSONObject RSI(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.RSI(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "OBV指标(递归)")
    @PostMapping("obv")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public Double OBV(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.OBV(code, date);
    }

    @SneakyThrows
    @ApiOperation(value = "RSV指标(递归)")
    @PostMapping("rsv")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", dataType = "String", required = false, defaultValue = "301299"),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String", required = false, defaultValue = "2022-12-21"),
    })
    public Double RSV(@RequestParam("code") String code, @RequestParam("date") String date) {
        return indexService.RSV(code, date);
    }
}