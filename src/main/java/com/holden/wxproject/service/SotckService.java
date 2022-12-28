package com.holden.wxproject.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.util.DataResult;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName wxproject-SotckService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日17:35 - 周一
 * @Describe
 */
public interface SotckService {
    DataResult<Object> getSingleStock(String code, Integer dateType, String value) throws SQLException;

    DataResult<String> getStockNum(String date);

    DataResult<List<Map<String, String>>> getIndustryReport(String date);

    DataResult<List<Map<String, Object>>> getContiniation(Integer times, Integer tag);

    DataResult<List<Map<String, Object>>> getContiniationFinance(Integer times, Integer tag);

    DataResult<List<Map<String, Object>>> judgeNews();

    DataResult<List<Map<String, Object>>> targetNews(String keyword);

    DataResult<List<Map<String, Object>>> targetStock(String code, String name);

    DataResult<List<Map<String, Object>>> stockHot();

    DataResult<List<String>> getStockCode(String date);

    DataResult<Object> getKline(String code, Integer dateType, String value);

    DataResult<Object> getCalendar(Integer dateType, String value);

    DataResult<Object> getMaxMinDs(String date);

}
