package com.holden.wxproject.service;

import com.alibaba.fastjson.JSONObject;
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
    Double MA(String code, Integer day, String date);

    Double BIAS(String code, Integer day, String date);

    Double WR(String code, Integer day, String date);

    Double ROC(String code, Integer day, String date);

    Double PSY(String code, Integer day, String date);

    Double ATR(String code, Integer day, String date);

    Double EMV(String code, String date, Integer day);

    Double DPO(String code, String date, Integer day, Integer circle);

    Double MTM(String code, String date, Integer circle);

    Double ASI(String code, String date, Integer day);

    JSONObject ENE(String code, Integer day, String date);

    JSONObject BRAR(String code, Integer day, String date);

    Double BBI(String code, String date);

    JSONObject KDJ(String code, String date);

    JSONObject MACD(String code, String date);

    Double SAR(String code, String date);

    JSONObject DMI(String code, String date);
    JSONObject MIKE(String code, String date);
    JSONObject RSI(String code, String date);
    Double OBV(String code, String date);
    Double RSV(String code, String date);
}
