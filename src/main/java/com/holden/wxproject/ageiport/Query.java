package com.holden.wxproject.ageiport;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName wxproject-Query
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日10:04 - 周二
 * @Describe
 */
@Getter
@Setter
public class Query {
    private Integer totalCount = 10000;
    private List<View> checkErrorData;
    private List<View> writeErrorData;
}
