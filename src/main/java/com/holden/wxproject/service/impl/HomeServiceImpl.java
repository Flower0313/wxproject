package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.config.ExecutorConfig;
import com.holden.wxproject.mapper.IndexMapper;
import com.holden.wxproject.pojo.resp.*;
import com.holden.wxproject.redis.RedisDao;
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
import java.util.Objects;
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

    @Autowired
    private RedisDao redisDao;

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public JSONObject getInfo() throws Exception {
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.format(DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT_OUTPUT_ACCURATE_TIME_DAY));

        //若redis有直接返回
        Object redisResult = null;
        boolean redisAlive = true;
        try {
            redisResult = redisDao.get(date);
            if (Objects.nonNull(redisResult)) {
                return JSONObject.parseObject(JSONObject.toJSONString(redisResult));
            }
        } catch (Exception e) {
            redisAlive = false;
            log.info("redis挂了");
        }


        CompletableFuture<List<OneRowResp>> oneRowResp = CompletableFuture.supplyAsync(() -> indexMapper.oneRow(date));
        CompletableFuture<List<TwoRowResp>> twoRowResp = CompletableFuture.supplyAsync(() -> indexMapper.twoRow());
        CompletableFuture<List<ThreeRowResp>> threeRowResp = CompletableFuture.supplyAsync(() -> indexMapper.threeRow());
        CompletableFuture<List<FourRowResp>> fourRowResp = CompletableFuture.supplyAsync(() -> indexMapper.fourRow(date));
        CompletableFuture<List<NewsInfoResp>> newsInfoResp = CompletableFuture.supplyAsync(() -> indexMapper.newInfo());
        CompletableFuture<List<BoardResp>> boardInfoResp = CompletableFuture.supplyAsync(() -> indexMapper.boardInfo(date));
        CompletableFuture<List<IndustryReport>> industry = CompletableFuture.supplyAsync(() -> indexMapper.industryReport());
        CompletableFuture<List<ChanceResp>> chance = CompletableFuture.supplyAsync(() -> indexMapper.chance());
        CompletableFuture<List<IndexResp>> index = CompletableFuture.supplyAsync(() -> indexMapper.bigIndex());
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(oneRowResp, twoRowResp, threeRowResp, fourRowResp, newsInfoResp, boardInfoResp, industry, chance, index);
        allFutures.join(); // 等待所有CompletableFuture执行完毕


        //返回结果
        JSONObject result = new JSONObject();
        result.put("t1", oneRowResp.join());
        result.put("t2", twoRowResp.join());
        result.put("t3l", threeRowResp.join());
        result.put("t3r", fourRowResp.join());
        result.put("news", newsInfoResp.join());
        result.put("board", boardInfoResp.join());
        result.put("industry", industry.join());
        result.put("chance", chance.join());
        result.put("index", index.join());

        //存入redis，设置24小时过期
        if (redisAlive) {
            redisDao.setHour(date, result, 12);
        }


        return result;
    }
}
