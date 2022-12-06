package com.holden.wxproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName wxproject-SourceChange
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月05日14:02 - 周一
 * @Describe
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SourceChange {
    String value();
}
