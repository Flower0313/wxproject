package com.holden.wxproject.annotation;

import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.util.DataSourceUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @ClassName wxproject-DataBaseAop
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月29日17:39 - 周四
 * @Describe 使用注解的方式切换数据源
 */
@Aspect
@Component
public class DataBaseAop {
    @Before("within(com.holden.wxproject.service.impl.*) && @annotation(dataBase)")
    public void setDataSource(DataBase dataBase) {
        DataSourceUtil.setDB(dataBase.value());
    }

    @After("@annotation(DataBase)")
    public void restoreDataBase() {
        DataSourceUtil.setDB(BaseConstant.SPIDER);
    }
}
