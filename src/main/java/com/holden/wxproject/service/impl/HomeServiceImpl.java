package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.config.ExecutorConfig;
import com.holden.wxproject.mapper.IndexMapper;
import com.holden.wxproject.pojo.resp.*;
import com.holden.wxproject.service.HomeService;
import com.holden.wxproject.util.DataResult;
import com.holden.wxproject.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName wxproject-HomeServiceImpl
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月27日14:46 - 周四
 * @Describe
 */
@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
    @Resource
    private IndexMapper indexMapper;

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public DataResult<JSONObject> getInfo() throws Exception {
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.format(DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT_OUTPUT_ACCURATE_TIME_DAY));


        CompletableFuture<List<OneRowResp>> oneRowResp = CompletableFuture.supplyAsync(() -> indexMapper.oneRow(date));
        CompletableFuture<List<TwoRowResp>> twoRowResp = CompletableFuture.supplyAsync(() -> indexMapper.twoRow());
        CompletableFuture<List<ThreeRowResp>> threeRowResp = CompletableFuture.supplyAsync(() -> indexMapper.threeRow());
        CompletableFuture<List<FourRowResp>> fourRowResp = CompletableFuture.supplyAsync(() -> indexMapper.fourRow(date));
        CompletableFuture<List<NewsInfoResp>> newsInfoResp = CompletableFuture.supplyAsync(() -> indexMapper.newInfo(date));
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(oneRowResp, twoRowResp, threeRowResp, fourRowResp, newsInfoResp);
        allFutures.join(); // 等待所有CompletableFuture执行完毕


        //返回结果
        JSONObject result = new JSONObject();
        result.put("t1", oneRowResp.join());
        result.put("t2", twoRowResp.join());
        result.put("t3l", threeRowResp.join());
        result.put("t3r", fourRowResp.join());
        result.put("news", newsInfoResp.join());

        return DataResult.ok(result);
    }
}
