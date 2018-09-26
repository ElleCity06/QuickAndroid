package com.ellecity06.quickandroid.demo.quailfier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PoetryQualifier {
    String value() default "";
}