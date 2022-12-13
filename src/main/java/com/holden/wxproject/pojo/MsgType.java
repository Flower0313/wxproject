package com.holden.wxproject.pojo;

/**
 * @ClassName wxproject-MsgType
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日19:05 - 周二
 * @Describe
 */
public enum MsgType {
    Text("text"),
    Image("image"),
    Music("music"),
    Video("video"),
    Voice("voice"),
    Location("location"),
    Link("link");
    private String msgType = "";

    MsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return msgType;
    }
}
