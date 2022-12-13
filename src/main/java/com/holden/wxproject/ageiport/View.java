package com.holden.wxproject.ageiport;

import com.alibaba.ageiport.processor.core.annotation.ViewField;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName wxproject-View
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日10:04 - 周二
 * @Describe
 */
@Getter
@Setter
public class View {
    @ViewField(headerName = "编码")
    private Integer id;
    @ViewField(headerName = "姓名")
    private String name;
}

