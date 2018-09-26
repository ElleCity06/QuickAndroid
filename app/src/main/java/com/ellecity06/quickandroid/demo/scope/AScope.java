package com.ellecity06.quickandroid.demo.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author ellecity06
 * @e-mail ellecity06@sina.com
 * @time 2018/9/19 15:03
 * @des 用于AComponent的作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AScope {
}
