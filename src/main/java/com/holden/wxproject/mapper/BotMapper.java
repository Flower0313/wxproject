package com.holden.wxproject.mapper;

import com.holden.wxproject.pojo.StockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.telegram.telegrambots.meta.api.objects.Chat;

import java.util.List;

/**
 * @ClassName wxproject-BotMapper
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月10日16:25 - 周一
 * @Describe
 */
@Mapper
public interface BotMapper {
    /**
     * 查询用户是否在数据库中存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    Integer ifExist(@Param("username") String username);

    /**
     * 添加新用户
     *
     * @param chat 用户pojo
     */
    void insert(@Param("Query") Chat chat);

    /**
     * 查看用户余额
     *
     * @param username 用户名
     * @return @link{Double} 余额值
     */
    Double balance(@Param("username") String username);
}
