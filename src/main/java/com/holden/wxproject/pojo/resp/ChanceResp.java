package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-chanceResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年5月11日15:48 - 周四
 * @Describe
 */
@Data
public class ChanceResp {
    private String code;
    private String totalScore;
    private String probability;
    private String rankRatio;
    private String increase;
}
