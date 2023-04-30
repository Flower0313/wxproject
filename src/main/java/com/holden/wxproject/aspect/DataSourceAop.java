package com.holden.wxproject.aspect;

import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * @ClassName wxproject-DataSourceAop
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月05日14:32 - 周一
 * @Describe
 */
@Aspect
@Component
public class DataSourceAop {
    //在nursing方法前执行
    @Before("within(com.holden.wxproject.service.impl.*) && @annotation(sourceChange)")
    public void setDataSource2test01(JoinPoint point, SourceChange sourceChange) throws Throwable {
        String value = sourceChange.value();
        if (value.equals("sexy")) {
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Sexy);
        } else if (value.equals("spider")) {
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Spider);
        } else {
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Spider);//默认使用主数据库
        }
    }


    @After("@annotation(sourceChange)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, SourceChange sourceChange) {
        DataSourceType.clearDataBaseType();
    }

}

