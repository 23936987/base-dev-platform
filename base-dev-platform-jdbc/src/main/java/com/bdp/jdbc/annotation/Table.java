package com.bdp.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 注解 表
 * <p>完成日期：2017年01月20日</p>
 *

 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "";
    String base() default "";
    String nameCn() default "";
    String label() default "";
    String comment() default "";

}
