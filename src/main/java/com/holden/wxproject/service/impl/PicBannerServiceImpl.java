package com.holden.wxproject.service.impl;

import com.holden.wxproject.mapper.PicBannerMapper;
import com.holden.wxproject.pojo.PicBanner;
import com.holden.wxproject.service.PicBannerService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class PicBannerServiceImpl implements PicBannerService {
    @Autowired
    private PicBannerMapper picBannerMapper;

    @Override
    public List<PicBanner> findAllPic() {
        return picBannerMapper.findAllPic();
    }

    @Override
    public void getPic(HttpServletResponse resp, Long picId) throws IOException {
        FileSystemResource file = new FileSystemResource("D:\\WeChatProjects\\SeniorNursing\\pages\\index\\imgs\\1.jpg");
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
            e.printStackTrace();
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
