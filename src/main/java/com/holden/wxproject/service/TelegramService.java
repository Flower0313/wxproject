package com.holden.wxproject.service;

/**
 * @ClassName wxproject-TelegramService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月28日10:26 - 周三
 * @Describe
 */
public interface TelegramService {

    /**
     * 更新用户积分
     *
     * @param id    用户编号
     * @param score 积分
     */
    String add(String id, Integer score);

    /**
     * 查询用户
     *
     * @return
     */
    String select();
}
