package com.ellecity06.quickjar.di.module;


import com.ellecity06.quickjar.bus.BusConfig;
import com.ellecity06.quickjar.cache.CacheConfig;
import com.ellecity06.quickjar.image.support.ImageConfig;
import com.ellecity06.quickjar.other.OtherConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * 各模块配置的供应Module
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:35
 */

@Module
public class ConfigModule {

    @Provides
    @Singleton
    BusConfig busConfig() {
        return new BusConfig();
    }

    @Provides
    @Singleton
    ImageConfig imageConfig() {
        ImageConfig imageConfig = new ImageConfig();
        imageConfig.setIsUseOkhttp(true);//默认使用okhttp3替换网络组件
        return imageConfig;
    }

    @Provides
    @Singleton
    CacheConfig cacheConfig() {
        return new CacheConfig();
    }

    @Provides
    @Singleton
    OtherConfig crashDiaryConfig() {
        return new OtherConfig();
    }


}
