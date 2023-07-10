package com.holden.wxproject.service.impl;

import com.holden.wxproject.annotation.DataBase;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.mapper.TelegramMapper;
import com.holden.wxproject.pojo.UserVO;
import com.holden.wxproject.service.TelegramService;
import com.holden.wxproject.util.DataSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String add(String id, Integer score) {
        try {
            DataSourceUtil.setDB(BaseConstant.PHOENIX);
            telegramMapper.add(id, score);
            return "success";
        } catch (Exception e) {
            return "fail";
        }

    }

    @Override
    @DataBase(BaseConstant.PHOENIX)
    public String select() {
        List<UserVO> select = telegramMapper.select();
        StringBuilder sb = new StringBuilder();
        for (UserVO vo : select) {
            sb.append(vo.getUserId()).append("\t").append(vo.getType()).append("\t").append(vo.getBalance()).append("<br>");
        }
        return sb.toString();
    }
}
