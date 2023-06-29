package com.holden.wxproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName wxproject-TelegramMapper
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月28日10:34 - 周三
 * @Describe
 */
@Mapper
public interface TelegramMapper {
    /**
     * 修改余额
     *
     * @param id    用户编号
     * @param score 积分
     */
    void add(@Param("id") String id, @Param("score") Integer score);
}
