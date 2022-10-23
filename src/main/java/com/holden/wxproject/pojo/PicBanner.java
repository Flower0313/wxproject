package com.holden.wxproject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName wxproject-PicBanner
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月22日14:46 - 周六
 * @Describe 只有返回接口中存在实体类对象，swagger才会扫描
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "轮播图")
public class PicBanner {
    @ApiModelProperty(value = "图片id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "图片名称", dataType = "String", example = "轮播图1")
    private String picName;
    @ApiModelProperty(value = "图片URL", dataType = "String", example = "1.jpg")
    private String picUrl;
}
