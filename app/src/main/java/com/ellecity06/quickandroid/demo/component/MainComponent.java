package com.ellecity06.quickandroid.demo.component;

import com.ellecity06.quickandroid.ApiApplication;
import com.ellecity06.quickandroid.MainActivity;
import com.ellecity06.quickandroid.demo.OtherActivity;
import com.ellecity06.quickandroid.demo.module.MainModule;
import com.ellecity06.quickandroid.demo.module.PoetryModule;
import com.ellecity06.quickandroid.demo.scope.PoetryScope;

import dagger.Component;

/**
 * @author ellecity06
 * @time 2018/9/19 9:37
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.component
 * @des TODO
 */
// 添加component注解。标记其作用,关联Module,这样就可以多了一个Module里面的GSON注入 ,PoetryModule用于生成带参数的实例
// 添加作用域以保证单例，记得Module也是需要加上的 . dependencies 是依赖的意思
@PoetryScope
@Component(dependencies = ApplicationComponent.class, modules = {MainModule.class, PoetryModule.class})
public abstract class MainComponent {
    public abstract void inject(MainActivity mainActivity);

    public abstract void inject(OtherActivity otherActivity);

    //做一个单例。
    private static MainComponent sMainComponent;

    public static MainComponent getInstance() {
        if (sMainComponent == null) {
            sMainComponent = DaggerMainComponent.builder()
                    .applicationComponent(ApiApplication.getApiApplication()
                    .getComponent()).build();
        }
        return sMainComponent;
    }
}
