package com.ellecity06.quickandroid.demo.component;

import com.ellecity06.quickandroid.demo.AActivity;
import com.ellecity06.quickandroid.demo.module.AModule;
import com.ellecity06.quickandroid.demo.scope.AScope;

import dagger.Subcomponent;

/**
 * @author ellecity06
 * @time 2018/9/19 15:05
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.component
 * @des 为了演示@SubComponent 包含方式注解.记得加上作用域
 */
@AScope
@Subcomponent(modules = AModule.class)
public interface ACompoment {
    void inject(AActivity aActivity);

}
