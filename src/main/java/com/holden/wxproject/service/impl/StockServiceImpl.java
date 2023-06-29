package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.mapper.StockMapper;
import com.holden.wxproject.pojo.StockTypeEnum;
import com.holden.wxproject.service.SotckService;
import com.holden.wxproject.util.ClickHouseUtil;
import com.holden.wxproject.util.DataResult;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName wxproject-StockServiceImpl
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日17:54 - 周一
 * @Describe
 */
@Service
@Slf4j
public class StockServiceImpl implements SotckService {
    @Autowired
    private StockMapper stockMapper;

    public boolean timeIsLegel(String str) {
        Pattern p = Pattern.compile("\\d{4}\\-\\d{1,2}\\-\\d{1,2}");//构造一个模式
        Matcher m = p.matcher(str);//构造一个匹配器
        return m.matches();
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<Object> getSingleStock(String code, Integer dateType, String value) {
        if ("".equals(code) || "".equals(value) || code == null || value == null) {
            return DataResult.fail("值不能为空!");
        }
        try {
            List<Map<String, String>> singleStock = stockMapper.getSingleStock(code, dateType, value);
            if (singleStock.size() > 0) {
                return DataResult.ok(singleStock);
            }
            return DataResult.fail("未查询到数据！");

        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getSingleStock]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("出错了！");
        }
    }

    @Override
    public DataResult<String> getStockNum(String date) {
        if ("".equals(date) || date == null) {
            return DataResult.fail("值不能为空!");
        }
        if (!timeIsLegel(date)) {
            return DataResult.fail("日期不正确!");
        }
        String result = "";
        try {
            Connection connection = ClickHouseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) as num from spider_base.stock_detail where ds ='" + date + "'");
            if (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getStockNum]-[function: {}] , [Message]: {}", e.getMessage(), e);
        }
        return DataResult.ok(result);

    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, String>>> getIndustryReport(String date) {
        List<Map<String, String>> industryReport = stockMapper.getIndustryReport(date);
        return DataResult.ok(industryReport);
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, Object>>> getContiniation(Integer times, Integer tag) {
        try {
            if (Objects.isNull(times) || Objects.isNull(tag)) {
                return DataResult.fail("请传值!");
            }
            List<Map<String, Object>> results = stockMapper.getContiniation(times, tag);
            //在内存中对累计值进行排序
            if (tag == 1) {
                results.sort((o1, o2) -> Integer.compare(0, new BigDecimal(o1.get("sumrate").toString()).compareTo(new BigDecimal(o2.get("sumrate").toString()))));
            } else {
                results.sort((o1, o2) -> Integer.compare(0, new BigDecimal(o2.get("sumrate").toString()).compareTo(new BigDecimal(o1.get("sumrate").toString()))));
            }
            return DataResult.ok(results);
        } catch (Exception e) {
            return DataResult.fail("股票出错");
        }

    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, Object>>> getContiniationFinance(Integer times, Integer tag) {
        try {
            if (Objects.isNull(times) || Objects.isNull(tag)) {
                return DataResult.fail("请传值!");
            }
            List<Map<String, Object>> results = stockMapper.getContiniationFinance(times, tag);
            //在内存中对累计值进行排序
            if (tag == 1) {
                results.sort((o1, o2) -> Integer.compare(0, new BigDecimal(o1.get("sumprice").toString()).compareTo(new BigDecimal(o2.get("sumprice").toString()))));
            } else {
                results.sort((o1, o2) -> Integer.compare(0, new BigDecimal(o2.get("sumprice").toString()).compareTo(new BigDecimal(o1.get("sumprice").toString()))));
            }
            return DataResult.ok(results);
        } catch (Exception e) {
            return DataResult.fail("融资融券");
        }

    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, Object>>> judgeNews() {
        try {
            List<Map<String, Object>> keywords = stockMapper.keywords();
            //String rlike = keywords.stream().map(String::valueOf).collect(Collectors.joining("|"));
            Map<Object, List<Map<String, Object>>> tag = keywords.stream().collect(Collectors.groupingBy(x -> x.get("tag")));
            String up = tag.get("正").get(0).get("content").toString().replace(",", "|");
            String down = tag.get("负").get(0).get("content").toString().replace(",", "|");
            List<Map<String, Object>> result = stockMapper.judgeNews(up, down);
            return DataResult.ok(result);
        } catch (Exception e) {
            return DataResult.fail("消息出错");
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, Object>>> targetNews(String keyword) {
        try {
            List<Map<String, Object>> maps = stockMapper.targetNews(keyword);
            return DataResult.ok(maps);
        } catch (Exception e) {
            return DataResult.fail("新闻消息出错");
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, Object>>> targetStock(String code, String name) {
        List<Map<String, Object>> maps = stockMapper.targetStock(code, name);
        return DataResult.ok(maps);
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<Map<String, Object>>> stockHot() {
        List<Map<String, Object>> result = stockMapper.sotckHot();
        return DataResult.ok(result);
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<List<String>> getStockCode(String date) {
        try {
            List<String> stockCode = stockMapper.getStockCode(date);
            return DataResult.ok(stockCode);
        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getStockCode]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("getStockCode出错了");
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<Object> getKline(String code, Integer dateType, String value) {
        try {
            List<Map<String, Object>> kline = stockMapper.getKline(code, dateType, value);
            return DataResult.ok(kline);
        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getKline]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("getStockCode出错了");
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<Object> getCalendar(Integer dateType, String value) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("('").append(String.join("','", value.split(","))).append("')");
            List<Map<String, Object>> kline = stockMapper.getCalendar(dateType, sb.toString());
            return DataResult.ok(kline);
        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getCalendar]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("getCalendar出错了");
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<Object> getMaxMinDs(String date) {
        try {
            List<Map<String, Object>> maxMinDs = stockMapper.getMaxMinDs(date);
            return DataResult.ok(maxMinDs);
        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getMaxMinDs]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("getMaxMinDs出错了");
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<Object> getBigMarketStocks(String date, Double ratio, String order, Integer type) {
        try {
            if (!("desc").equals(order) && !("asc").equals(order)) {
                return DataResult.fail("排序方式错误，只能选desc或asc");
            }
            StockTypeEnum column = StockTypeEnum.getType(type);
            if (Objects.isNull(column)) {
                StringBuilder sb = new StringBuilder();
                sb.append("未找到对应的指标，请从以下这些指标中选择：");
                List<StockTypeEnum> stockEnum = StockTypeEnum.getStockEnum();
                for (StockTypeEnum stockTypeEnum : stockEnum) {
                    sb.append("(").append(stockTypeEnum.getType()).append(",").append(stockTypeEnum.getDesc()).append(")");
                }
                return DataResult.fail(sb.toString());
            }
            List<String> maxMinDs = stockMapper.getBigMarketStocks(date, ratio, order, column.getValue());
            return DataResult.ok(maxMinDs);
        } catch (Exception e) {
            log.error("[class: StockServiceImpl.java]-[method: getBigMarketStocks]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("getBigMarketStocks出错了");
        }
    }


}
