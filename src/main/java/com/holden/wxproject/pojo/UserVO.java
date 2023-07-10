package com.holden.wxproject.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName wxproject-UserVO
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年7月04日18:31 - 周二
 * @Describe
 */
@Data
public class UserVO {
    private String userId;
    private BigDecimal balance;
    private Integer type;
    private String updateTime;
}
