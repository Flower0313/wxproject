package com.holden.wxproject.service;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName wxproject-PicBannerService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:48 - 周六
 * @Describe
 */
public interface PicBannerService {
    JSONArray findAllPic();

    void getPic(HttpServletResponse resp, Long picId) throws IOException;
}
