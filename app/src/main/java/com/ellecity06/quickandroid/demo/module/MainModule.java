package com.ellecity06.quickandroid.demo.module;

import dagger.Module;

/**
 * @author ellecity06
 * @time 2018/9/19 10:03
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.module
 * @des TODO
 */
// 添加@Module注解，因为有的时候我们并不能直接在构造函数添加@inject注解，或者类中存在多个构造函数时
// @Inject也只能注解其中一个构造函数。所以针对这种情况我们需要用到@Module注解
@Module
public class MainModule {

    /**
     * @Provides 注解表示这个方法是用来创建某个实例对象的，这里我们创建返回Gson对象
     * 方法名随便，一般用provideXXX结构
     * @return 返回注入对象
     */
//    @Provides
//    public Gson provideGson(){
//        return new Gson();
//    }

}
