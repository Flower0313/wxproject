package com.holden.wxproject.service.impl;

import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.mapper.TelegramMapper;
import com.holden.wxproject.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName wxproject-TelegramServiceImpl
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月28日10:32 - 周三
 * @Describe
 */
@Service
@Slf4j
public class TelegramServiceImpl implements TelegramService {

    @Autowired
    private TelegramMapper telegramMapper;

    @Override
    @SourceChange(BaseConstant.PHOENIX)
    public String add(String id, Integer score) {
        try {
            telegramMapper.add(id, score);
            return "success";
        } catch (Exception e) {
            return "fail";
        }

    }
}
