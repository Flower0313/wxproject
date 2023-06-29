package com.holden.wxproject.util;

/**
 * @ClassName wxproject-DataSourceUtil
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月29日16:57 - 周四
 * @Describe
 */
public class DataSourceUtil {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    /**
     * 设置数据源名
     * @param dbType
     */
    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源名
     * @return
     */
    public static String getDB() {
        return contextHolder.get();
    }

    /**
     * 清除数据源名
     */
    public static void clearDB() {
        contextHolder.remove();
    }
}
