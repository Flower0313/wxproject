package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-oneRowResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月27日17:27 - 周四
 * @Describe 前两栏图标
 */
@Data
public class OneRowResp  extends Parents{
    /**
     * 主力净流入
     */
    private String mainFlow;

    /**
     * 散户净流入
     */
    private String smallFlow;

    /**
     * 最佳涨幅
     */
    private String upRate;

    /**
     * 最惨跌幅
     */
    private String downRate;

    /**
     * 外盘
     */
    private String outside;

    /**
     * 内盘
     */
    private String inner;

    /**
     * 今日成交量
     */
    private String dealVol;

    /**
     * 当前最高成交
     */
    private String highest;

}

