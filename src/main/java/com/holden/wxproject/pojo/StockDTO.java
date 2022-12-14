package com.holden.wxproject.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName wxproject-StockDTO
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月14日17:36 - 周三
 * @Describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "股票模型")
public class StockDTO {
    private String code;
    private String name;
    private Double current_price;
    private String ds;
    private Double highest;
    private Double lowest;
}
