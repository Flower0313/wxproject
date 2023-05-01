package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-NewsInfoResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月28日14:30 - 周五
 * @Describe
 */
@Data
public class NewsInfoResp extends Parents {
    /**
     * 股票代码
     */
    private String code;

    /**
     * 内容
     */
    private String content;

    /**
     * 机构
     */
    private String agency;

    /**
     * 日期
     */
    private String date;
}
