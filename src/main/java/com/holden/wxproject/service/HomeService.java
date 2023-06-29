package com.holden.wxproject.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @ClassName wxproject-HomeService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月27日14:46 - 周四
 * @Describe
 */
public interface HomeService {
    /**
     * 返回最新的股票信息
     *
     * @return data
     */
    JSONObject getInfo() throws Exception;


    /**
     * 返回图片
     *
     * @param url 链接
     */
    byte[] getImage(String url) throws IOException;

    void test();
}
