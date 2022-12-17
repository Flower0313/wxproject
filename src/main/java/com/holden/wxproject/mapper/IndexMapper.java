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
    List<StockDTO> MA(@Param("code") String code, @Param("day") Integer day, @Param("date") String date);

    List<StockDTO> List(@Param("code") String code, @Param("date") String date, @Param("day") Integer day);

    StockDTO current(@Param("code") String code, @Param("date") String date);

    Integer num(@Param("code") String code);

    List<String> ifExecute(@Param("date") String date);

    //包含今天
    List<String> getPreDate(@Param("date") String date, @Param("day") Integer day);

    String lastExecute(@Param("date") String date);

    Double getHighest(@Param("code") String code, @Param("date") String date);

    Double getLowest(@Param("code") String code, @Param("date") String date);

    Double getClosingPrice(@Param("code") String code, @Param("date") String date);

    String getTargetDate(@Param("code") String code, @Param("date") String date, @Param("day") Integer day);

    Double AVG(@Param("code") String code, @Param("date") String date, @Param("day") Integer day, @Param("field") String field);

    Double HIGH(@Param("code") String code, @Param("date") String date, @Param("day") Integer day, @Param("field") String field);

    Double LOW(@Param("code") String code, @Param("date") String date, @Param("day") Integer day, @Param("field") String field);
}
