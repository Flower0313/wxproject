package com.holden.wxproject.mapper;

import org.apache.ibatis.annotations.Mapper;

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
    List<Map<String,String>> getSingleStock();
}
