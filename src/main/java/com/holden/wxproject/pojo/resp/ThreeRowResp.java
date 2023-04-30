package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-ThreeRowResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月28日11:58 - 周五
 * @Describe
 */
@Data
public class ThreeRowResp  extends Parents{
    /**
     * 行业
     */
    private String industry;

    /**
     * 行业数量
     */
    private String industryNum;
}
