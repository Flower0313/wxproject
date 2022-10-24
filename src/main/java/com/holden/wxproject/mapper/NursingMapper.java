package com.holden.wxproject.mapper;

import com.holden.wxproject.pojo.NursingInfo;
import com.holden.wxproject.pojo.PicBanner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName wxproject-NursingMapper
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日14:17 - 周一
 * @Describe
 */
@Mapper
public interface NursingMapper {
    List<NursingInfo> findAllNursing();
}
