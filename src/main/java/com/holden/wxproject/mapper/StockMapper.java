package com.holden.wxproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName wxproject-StockMapper
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日17:57 - 周一
 * @Describe
 */
@Mapper
public interface StockMapper {
    List<Map<String, String>> getSingleStock(@Param("code") String code, @Param("dateType") Integer dateType, @Param("value") String value);

    List<Map<String, String>> getIndustryReport(@Param("date") String date);

    List<Map<String, Object>> getContiniation(@Param("times") int times, @Param("tag") int tag);

    List<Map<String, Object>> getContiniationFinance(@Param("times") int times, @Param("tag") int tag);

    List<Map<String, Object>> judgeNews(@Param("up") String up, @Param("down") String down);

    List<Map<String, Object>> keywords();

    List<Map<String, Object>> targetNews(@Param("target") String target);

    List<Map<String, Object>> targetStock(@Param("code") String code, @Param("name") String name);

    List<Map<String, Object>> sotckHot();

    List<String> getStockCode(@Param("date") String date);

    List<Map<String, Object>> getKline(@Param("code") String code, @Param("dateType") Integer dateType, @Param("value") String value);

    List<Map<String, Object>> getCalendar (@Param("dateType") Integer dateType, @Param("value") String value);

    List<Map<String, Object>> getMaxMinDs (@Param("date") String date);

    List<String> getBigMarketStocks (@Param("date") String date,@Param("ratio") Double ratio,@Param("order") String order,@Param("column") String column);
}
