package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
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
        //第一栏
        List<OneRowResp> oneRowResp = indexMapper.oneRow(date);
        //第二栏
        List<TwoRowResp> twoRowResp = indexMapper.twoRow();
        //第三栏左边
        List<ThreeRowResp> threeRowResp = indexMapper.threeRow();
        //第三栏右边
        List<FourRowResp> fourRowResp = indexMapper.fourRow(date);
        //新闻
        List<NewsInfoResp> newsInfoResp = indexMapper.newInfo(date);


        //返回结果
        JSONObject result = new JSONObject();
        result.put("t1", oneRowResp);
        result.put("t2", twoRowResp);
        result.put("t3l", threeRowResp);
        result.put("t3r", fourRowResp);
        result.put("news", newsInfoResp);

        return DataResult.ok(result);
    }
}
