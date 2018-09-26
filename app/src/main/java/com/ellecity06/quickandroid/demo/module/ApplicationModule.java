package com.ellecity06.quickandroid.demo.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author ellecity06
 * @time 2018/9/19 14:28
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.module
 * @des TODO
 */
@Module
public class ApplicationModule {


    //提供一个Gson对象 //@Singleton 注解
    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
