package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.config.DataSourceType;
import com.holden.wxproject.mapper.PicBannerMapper;
import com.holden.wxproject.pojo.PicBanner;
import com.holden.wxproject.service.PicBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName wxproject-PicBannerServiceImpl
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:49 - 周六
 * @Describe
 */
@Slf4j
@Service
public class PicBannerServiceImpl implements PicBannerService {
    @Autowired
    private PicBannerMapper picBannerMapper;

    @Value("${file.storeage}")
    private String FileStoreage;

    @Value("${file.http}")
    private String FileHttp;

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public JSONArray findAllPic() {
        List<PicBanner> allPic = picBannerMapper.findAllPic();
        JSONArray objects = new JSONArray();
        for (PicBanner picBanner : allPic) {
            objects.add(FileHttp + "?picId=" + picBanner.getId());
        }
        return objects;
    }

    @Override
    public void getPic(HttpServletResponse resp, Long picId) throws IOException {
        //获取图片的url名称
        String picUrl = picBannerMapper.getPicUrl(picId);
        FileSystemResource file = new FileSystemResource(FileStoreage + "/" + picUrl);
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        //将文件变成excel格式
        resp.setContentType("image/png");
        try {
            inputStream = file.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(resp.getOutputStream());
            FileCopyUtils.copy(bufferedInputStream, bufferedOutputStream);
        } catch (Exception e) {
            log.error("[class: PicBannerServiceImpl.java]-[method: getPic]-[function: {}] , [Message]: {}", e.getMessage(), e);
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != bufferedInputStream) {
                bufferedInputStream.close();
            }
            if (null != bufferedOutputStream) {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
        }
    }
}
