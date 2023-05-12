package com.holden.wxproject.pojo.resp;

import lombok.Data;

/**
 * @ClassName wxproject-IndexResp
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年5月12日10:27 - 周五
 * @Describe
 */
@Data
public class IndexResp {
    private String date;
    private String open;
    private String close;
    private String lowest;
    private String highest;
}
