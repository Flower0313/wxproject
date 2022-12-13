package com.holden.wxproject.controller;

import com.holden.wxproject.mapper.PicBannerMapper;
import com.holden.wxproject.service.PicBannerService;
import com.holden.wxproject.service.impl.PicBannerServiceImpl;
import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @ClassName wxproject-IndexController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月25日11:54 - 周二
 * @Describe
 */
@Api(tags = {"首页"})
@RestController
public class IndexController {
    @Autowired
    private PicBannerMapper picBannerMapper;

    @Value("${file.storeage}")
    private String FileStoreage;

    @Value("${file.http}")
    private String FileHttp;







}