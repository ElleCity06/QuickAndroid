package com.ellecity06.quickjar.bus.support.rxbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 接收事件注解，必须在接收事件地方定制该注解
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.MAIN_THREAD;
}
