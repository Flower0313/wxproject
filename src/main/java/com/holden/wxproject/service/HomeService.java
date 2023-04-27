package com.holden.wxproject.service;

import com.holden.wxproject.util.DataResult;

import java.util.List;
import java.util.Map;

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
    DataResult<List<Map<String, Object>>> getInfo();
}
