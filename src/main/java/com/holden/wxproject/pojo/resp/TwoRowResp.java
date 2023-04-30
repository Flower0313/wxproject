package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-TwoRowResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月28日11:53 - 周五
 * @Describe
 */
@Data
public class TwoRowResp extends Parents {
    /**
     * 日期
     */
    private String ds;

    /**
     * 大单
     */
    private String big;

    /**
     * 小单
     */
    private String small;
}
