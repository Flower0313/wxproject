package com.holden.wxproject.service;

import com.holden.wxproject.pojo.PicBanner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName wxproject-PicBannerService
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:48 - 周六
 * @Describe
 */
public interface PicBannerService {
    List<PicBanner> findAllPic();

    void getPic(HttpServletResponse resp, Long picId) throws IOException;
}
