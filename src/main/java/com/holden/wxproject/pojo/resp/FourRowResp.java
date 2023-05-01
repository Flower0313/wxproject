package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-FourRowResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月28日12:56 - 周五
 * @Describe
 */
@Data
public class FourRowResp extends Parents {
    /**
     * 股票代码
     */
    private String code ;

    /**
     * 股票名称
     */
    private String name ;

    /**
     * 当前价
     */
    private String currentPrice ;

    /**
     * 涨幅
     */
    private String upDownRate ;

    /**
     * 市盈率
     */
    private String pe ;

    /**
     * 成交量
     */
    private String dealAmount ;

    /**
     * 行业
     */
    private String industry ;
}
