package com.holden.wxproject;

import com.alibaba.fastjson.JSONArray;
import com.github.plexpt.chatgpt.Chatbot;
import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.mapper.PicBannerMapper;
import com.holden.wxproject.mapper.StockMapper;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

class WxprojectApplicationTests {
    public static void main(String[] args) {
        String value = "{\\" +
                "    \"access_token\": \"63_aGSW_INagq_Lvy3FPEqmJlCEw40zSSVlndATWJtS5Nu1Fyz2ay9EExsZW4YpynmDs7JS45ceCVlHu_Ymub_cdIGYxADMcVb7UkxlP3mIiCzDtWr79d-WwxB5bIQRTLjAIAHJB\",\\" +
                "    \"expires_in\": 7200\\" +
                "}";
        JSONArray json = (JSONArray) JSONArray.parse(value);
        System.out.println(json);
    }
}
