package com.holden.wxproject.mapper;

import com.holden.wxproject.pojo.PicBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName wxproject-PicBannerMapper
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:47 - 周六
 * @Describe
 */
@Mapper
public interface PicBannerMapper {
    List<PicBanner> findAllPic();

    String getPicUrl(@Param("picId") Long picId);
}
