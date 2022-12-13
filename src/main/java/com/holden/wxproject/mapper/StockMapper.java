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
    List<Map<String, String>> getSingleStock();

    List<Map<String, String>> getIndustryReport(@Param("date") String date);

    List<Map<String, Object>> getContiniation(@Param("times") int times, @Param("tag") int tag);

    List<Map<String, Object>> getContiniationFinance(@Param("times") int times, @Param("tag") int tag);

    List<Map<String, Object>> judgeNews(@Param("up") String up, @Param("down") String down);

    List<Map<String, Object>> keywords();

    List<Map<String, Object>> sotckHot();


}
