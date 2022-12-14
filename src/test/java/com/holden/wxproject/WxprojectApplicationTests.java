package com.holden.wxproject;

import cn.hutool.http.HttpUtil;
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
        String url = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + "dhasjkhdadhajdhakdhahkdjashda";
        String post = HttpUtil.post(url, (String) null);
        System.out.println(post);

    }
}
