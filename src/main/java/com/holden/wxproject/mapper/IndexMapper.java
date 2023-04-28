package com.holden.wxproject.mapper;

import com.holden.wxproject.pojo.StockDTO;
import com.holden.wxproject.pojo.resp.*;
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

    List<Map<String, Object>> MALIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> BIASLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> WRLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> ROCLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> ENELIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> PSYLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> BRARLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> ATRLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> EMVLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year, @Param("circle") Integer circle);

    List<Map<String, Object>> MTMLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

    List<Map<String, Object>> DPOLIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year, @Param("circle") Integer circle);

    List<Map<String, Object>> BBILIST(@Param("code") String code, @Param("year") String year);

    List<Map<String, Object>> KDJLIST(@Param("code") String code, @Param("year") String year);

    List<Map<String, Object>> MACDLIST(@Param("code") String code, @Param("year") String year);

    List<Map<String, Object>> ASILIST(@Param("code") String code, @Param("day") Integer day, @Param("year") String year);

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

    List<Map<String, Object>> recursiveIndex(@Param("code") String code, @Param("date") String date);

    /**
     * 第一栏信息
     *
     * @param date 当前日期
     * @return Map
     */
    List<OneRowResp> oneRow(@Param("date") String date);

    /**
     * @return 第二栏
     */
    List<TwoRowResp> twoRow();

    /**
     * @return 第三栏左边
     */
    List<ThreeRowResp> threeRow();

    /**
     * 第三栏右边
     *
     * @return 第三栏右边边
     */
    List<FourRowResp> fourRow(@Param("date") String date);

    List<NewsInfoResp> newInfo(@Param("date") String date);


}
