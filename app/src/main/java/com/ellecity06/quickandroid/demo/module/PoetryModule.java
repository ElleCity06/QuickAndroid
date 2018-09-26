package com.ellecity06.quickandroid.demo.module;

import com.ellecity06.quickandroid.demo.Poetry;
import com.ellecity06.quickandroid.demo.scope.PoetryScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author ellecity06
 * @time 2018/9/19 10:42
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo.module
 * @des TODO
 */

// Component 可以依赖多个Module，但是以上的构造方法或生成方法都是无参生成实例的，
// 如果我们带参数的话就需要多创造一个module来提供参数，需要用到Poetry注解
@Module
public class PoetryModule {
    // 这个方法需要一个String参数，在Dagger2注入中，这些参数也是注入形式的，也就是
    // 要有其他对方提供参数poems的生成，不然会造成编译出错
    // 加上@poetryScope注解报证单例
    @PoetryScope
    @Provides
    public Poetry providePoetry(String s) {
        return new Poetry(s);
    }

    // 这里提供了一个生成String的方法，在这个Module里生成Poetry实例时，会查找到这里
    // 可以为上面提供String类型的参数
    @Provides
    public String providePoems() {
        return "林夕用三十年的歌词来诠释求而不得";
    }
}
