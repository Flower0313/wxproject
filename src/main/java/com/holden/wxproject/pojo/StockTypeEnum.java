package com.holden.wxproject.pojo;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName wxproject-StockTypeEnum
 * @Athor Holden_-__--___Xiao
 * @Create 2023年1月01日19:37 - 周日
 * @Describe
 */
@Getter
@ToString
public enum StockTypeEnum {
    PE(10001, "PE_ratio_d", "市盈率（动）"),
    Market(10002, "total_market_v", "市值"),
    Ratio(10003, "price_to_b_ratio", "市净率"),
    GrossProfit(10004, "gross_profit_margin", "毛利率"),
    TotalAssets(10005, "total_assets", "总资产"),
    ROE(10006, "roe", "加权净资产收益率"),
    NetAssets(10008, "net_assets", "净资产"),
    PerProfit(10007, "profit", "每股收益");

    private Integer type;
    private String value;
    private String desc;

    StockTypeEnum(Integer type, String value, String desc) {
        this.type = type;
        this.value = value;
        this.desc = desc;
    }

    public static List<StockTypeEnum> getStockEnum(){
        return Arrays.asList(values());
    }

    public static StockTypeEnum getType(Integer type) {

        if (type == null) {
            return null;
        }

        for (StockTypeEnum each : values()) {
            if (each.getType().equals(type)) {
                return each;
            }
        }

        return null;
    }
}
