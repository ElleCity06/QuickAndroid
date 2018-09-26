package com.ellecity06.quickjar.di.component;

import android.app.Application;
import android.support.v4.util.SimpleArrayMap;

import com.ellecity06.quickjar.QuickAndroid;
import com.ellecity06.quickjar.base.activity.ActivityLifeCallback;
import com.ellecity06.quickjar.base.fragment.FragmentLifeCallback;
import com.ellecity06.quickjar.bus.BusConfig;
import com.ellecity06.quickjar.bus.support.IBusManager;
import com.ellecity06.quickjar.cache.CacheConfig;
import com.ellecity06.quickjar.cache.CacheManager;
import com.ellecity06.quickjar.db.support.ITableManger;
import com.ellecity06.quickjar.di.module.ConfigModule;
import com.ellecity06.quickjar.di.module.OtherModule;
import com.ellecity06.quickjar.di.module.ElleCityModule;
import com.ellecity06.quickjar.http.ElleCityHttp;
import com.ellecity06.quickjar.http2.HttpConfig;
import com.ellecity06.quickjar.http2.HttpManager;
import com.ellecity06.quickjar.image.support.IImageManager;
import com.ellecity06.quickjar.image.support.ImageConfig;
import com.ellecity06.quickjar.other.ActivityListManager;
import com.ellecity06.quickjar.other.CrashDiary;
import com.ellecity06.quickjar.other.OtherConfig;
import com.ellecity06.quickjar.other.PermissionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.OkHttpClient;


/**
 * 全局，单例，核心的连接器Component 用@Component来表示这个接口是一个连接器，只能用于接口或者抽象类
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:33
 */
@Singleton
@Component(modules = {ElleCityModule.class, OtherModule.class, ConfigModule.class})
public interface ElleCityComponent {

    Application application();//提供Application


    BusConfig busConfig();//提供事件总线配置

    ImageConfig imageConfig();//提供图片加载配置

    CacheConfig cacheConfig();//提供缓存配置

    HttpConfig httpConfig();//提供网络请求配置

    OtherConfig otherConfig();//提供其他模块的配置


    IBusManager busManager();//提供事件总线管理者

    IImageManager imageManager();//提供图片加载管理者

    CacheManager cacheManager();//提供缓存管理者

    HttpManager httpManager();//提供网络请求的管理者

    ElleCityHttp ellecityHttp();// 自封装的网络请求库

    CrashDiary crashDiary();//提供崩溃日志管理者

    ActivityListManager activityListManager();

    PermissionManager permissionManager();//提供权限管理的管理者

    OkHttpClient okHttpClient();//提供OkHttpClient

    SimpleArrayMap<Object, ITableManger> mapTableManager();//提供存放表管理者的map

    ActivityLifeCallback activityLifeCallback();

    FragmentLifeCallback fragmentLifeCallback();

    void inject(QuickAndroid quickAndroid);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ElleCityComponent build();
        //
    }
}
