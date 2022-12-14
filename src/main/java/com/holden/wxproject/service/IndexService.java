package com.holden.wxproject.service;

import com.holden.wxproject.pojo.StockDTO;
import com.holden.wxproject.util.DataResult;

import java.util.List;
import java.util.Map;

/**
 * @ClassName wxproject-IndexService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月14日16:51 - 周三
 * @Describe
 */

public interface IndexService {
    Double MA(String code, Integer day);

    Double BIAS(String code, Integer day);

    Double RSI(String code, Integer day);

    Double WR(String code, Integer day);

    Double ROC(String code, Integer day);

    Double UPPER_ENE(String code, Integer day);

    Double BBI(String code);

}
