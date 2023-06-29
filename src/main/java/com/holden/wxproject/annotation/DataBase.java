package com.holden.wxproject.annotation;

import com.holden.wxproject.config.BaseConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName wxproject-DataBase
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年6月29日17:37 - 周四
 * @Describe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataBase {
    String value() default BaseConstant.SPIDER;
}
