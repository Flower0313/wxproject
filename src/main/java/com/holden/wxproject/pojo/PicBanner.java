package com.holden.wxproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName wxproject-PicBanner
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:46 - 周六
 * @Describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PicBanner {
    private Long id;
    private String picName;
    private String picUrl;
}
