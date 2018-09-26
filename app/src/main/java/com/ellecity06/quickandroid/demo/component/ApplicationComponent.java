package com.ellecity06.quickandroid.demo.component;

import com.ellecity06.quickandroid.demo.module.AModule;
import com.ellecity06.quickandroid.demo.module.ApplicationModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author ellecity06
 * @time 2018/9/19 14:31
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.component
 * @des TODO
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Gson getGson();

    //添加声明
    ACompoment plus(AModule aModule);
}
