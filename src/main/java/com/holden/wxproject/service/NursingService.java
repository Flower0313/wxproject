package com.holden.wxproject.service;

import com.alibaba.fastjson.JSONArray;
import com.holden.wxproject.pojo.NursingInfo;

import java.util.List;

/**
 * @ClassName wxproject-NursingService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日14:14 - 周一
 * @Describe
 */
public interface NursingService {
    List<NursingInfo> getAllNursing();
}
