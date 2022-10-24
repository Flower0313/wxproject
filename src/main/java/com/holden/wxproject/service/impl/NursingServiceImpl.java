package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.holden.wxproject.mapper.NursingMapper;
import com.holden.wxproject.pojo.NursingInfo;
import com.holden.wxproject.service.NursingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName wxproject-NursingServiceImpl
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日14:16 - 周一
 * @Describe
 */
@Service
@Slf4j
public class NursingServiceImpl implements NursingService {
    @Autowired
    private NursingMapper nursingMapper;

    @Override
    public List<NursingInfo> getAllNursing() {
        List<NursingInfo> allNursing = nursingMapper.findAllNursing();
        return allNursing;
    }
}
