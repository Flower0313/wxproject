package com.holden.wxproject.config;

/**
 * @ClassName wxproject-DataSourceType
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月05日14:30 - 周一
 * @Describe
 */
public class DataSourceType {

    //内部枚举类，用于选择特定的数据类型
    public enum DataBaseType {
        Phoenix, Spider
    }

    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataBaseType> TYPE = new ThreadLocal<DataBaseType>();

    // 往当前线程里设置数据源类型
    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        TYPE.set(dataBaseType);
    }

    // 获取数据源类型
    public static DataBaseType getDataBaseType() {
        DataBaseType dataBaseType = TYPE.get() == null ? DataBaseType.Phoenix : TYPE.get();
        return dataBaseType;
    }

    // 清空数据类型
    public static void clearDataBaseType() {
        TYPE.remove();
    }

}
