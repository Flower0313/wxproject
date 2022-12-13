package com.holden.wxproject.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @ClassName wxproject-MediaIdMessage
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日19:03 - 周二
 * @Describe
 */
@Data
public class MediaIdMessage {
    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String[] MediaId;
}
