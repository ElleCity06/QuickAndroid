package com.ellecity06.quickandroid.demo.module;

import com.ellecity06.quickandroid.demo.Poetry;
import com.ellecity06.quickandroid.demo.quailfier.PoetryQualifier;
import com.ellecity06.quickandroid.demo.scope.AScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author ellecity06
 * @time 2018/9/19 15:06
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.module
 * @des TODO
 */
//记得加上作用域
@AScope
@Module
public class AModule {

    // 不要忘记@Provides注解。这个注解是提供数据对象的
    @PoetryQualifier("A")
    @AScope
    @Provides
    public Poetry providePoetry() {
        return new Poetry("我只是为了演示SubComponent这个注解的而已。");
    }

    // 新加一个方法，为了演示@Qualifler注解，用于区分两个provides
    @PoetryQualifier("B")
    @AScope
    @Provides
    public Poetry getOtherPoetry() {
        return new Poetry("我只是为了演示Qualifier这个注解的而已。");
    }
}
