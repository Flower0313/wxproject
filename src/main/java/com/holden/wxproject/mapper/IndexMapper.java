package com.holden.wxproject.mapper;

import com.holden.wxproject.pojo.StockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName wxproject-IndexMapper
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月14日16:53 - 周三
 * @Describe
 */
@Mapper
public interface IndexMapper {
    List<StockDTO> MA(@Param("code") String code, @Param("day") Integer day);

    List<StockDTO> BIAS(@Param("code") String code, @Param("day") Integer day);

    List<StockDTO> WR(@Param("code") String code, @Param("day") Integer day);

    List<StockDTO> List(@Param("code") String code, @Param("day") Integer day);

    Integer num(@Param("code") String code);
}
