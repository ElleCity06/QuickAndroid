package com.ellecity06.quickjar;

import android.app.Application;

import com.ellecity06.quickjar.bus.BusConfig;
import com.ellecity06.quickjar.bus.EventBusManager;
import com.ellecity06.quickjar.bus.support.IBusManager;
import com.ellecity06.quickjar.cache.CacheConfig;
import com.ellecity06.quickjar.cache.CacheManager;
import com.ellecity06.quickjar.db.support.IDBManager;
import com.ellecity06.quickjar.db.support.ITableManger;
import com.ellecity06.quickjar.di.component.DaggerElleCityComponent;
import com.ellecity06.quickjar.di.component.ElleCityComponent;
import com.ellecity06.quickjar.http.ElleCityHttp;
import com.ellecity06.quickjar.http2.HttpConfig;
import com.ellecity06.quickjar.http2.HttpManager;
import com.ellecity06.quickjar.image.support.IImageManager;
import com.ellecity06.quickjar.image.support.ImageConfig;
import com.ellecity06.quickjar.other.ActivityListManager;
import com.ellecity06.quickjar.other.OtherConfig;
import com.ellecity06.quickjar.other.PermissionManager;
import com.ellecity06.quickjar.other.ElleCityLog;
import com.ellecity06.quickjar.util.Preconditions;
import com.ellecity06.quickjar.util.ElleCityToast;


/**
 * QuickAndroid库核心的操作类
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 14:42
 */

public class QuickAndroid {

    private static ElleCityComponent sMElleCityComponent;
    private static IDBManager mDBManager;
    private static IBusManager mBusManager;
    private static IImageManager mImageManager;

    /**
     * 初始化操作
     */
    public static void init(Application application) {
        sMElleCityComponent = DaggerElleCityComponent.builder().application(application).build();//如果提示找不到DaggerElleCityComponent类，请重新编译下项目。
        application.registerActivityLifecycleCallbacks(sMElleCityComponent.activityLifeCallback());
    }

    /**
     * 开始构建
     */
    public static void create() {
        //数据库模块的构建工作
        if (mDBManager != null) {
            mDBManager.init();
            mDBManager.putTableManager(sMElleCityComponent.mapTableManager());
        }

        //事件总线模块的构建工作
        if (busManager() instanceof EventBusManager) {
            ((EventBusManager) busManager()).openIndex();
        }

        //图片加载模块的构建工作
        imageManager().init(sMElleCityComponent.application(), sMElleCityComponent.imageConfig());

        //其他模块的构建工作
        //崩溃日志
        if (sMElleCityComponent.otherConfig().isUseCrashDiary()) {
            sMElleCityComponent.crashDiary().init(sMElleCityComponent.application(), sMElleCityComponent.otherConfig().getCrashDiaryFolder());
        }
        //ElleCityLog
        ElleCityLog.init(sMElleCityComponent.otherConfig().isShowRingLog());
        //ElleCityToast
        ElleCityToast.init(sMElleCityComponent.application());
        ElleCityHttp.init(sMElleCityComponent.application());
    }

    /**
     * 获取ElleCityComponent为空，从而获取ElleCityComponent为空中提供的各对象。
     */
    public static ElleCityComponent elleCityComponent() {
        return Preconditions.checkNotNull(sMElleCityComponent, "ElleCityComponent为空，请先在Application中调用QuickAndroid.init(Application)方法进行初始化");
    }

    /**
     * 配置数据库模块
     */
    public static void configureDB(IDBManager dbManager) {
        mDBManager = dbManager;
    }

    /**
     * 获取数据库管理者
     */
    public static <T extends IDBManager> T dbManager() {
        return (T) Preconditions.checkNotNull(mDBManager, "请先在Application中调用QuickAndroid.configureDB(IDBManager)方法设置数据库管理类");
    }

    /**
     * 获取数据表管理者
     */
    public static <T extends ITableManger> T tableManager(Object key) {
        return (T) Preconditions.checkNotNull(sMElleCityComponent.mapTableManager().get(key), "没找到该Key值对应的数据表管理者，请检查IDBManager实现类中的putTableManager(Map<Object,ITableManager>)方法");
    }

    /**
     * 配置事件总线模块
     */
    public static BusConfig configureBus() {
        return sMElleCityComponent.busConfig();
    }

    /**
     * 配置事件总线模块，用于替换默认的EventBus
     *
     * @param busManager 要替换EventBus的事件总线管理者
     */
    public static void configureBus(IBusManager busManager) {
        mBusManager = busManager;
    }

    /**
     * 获取事件总线管理者
     */
    public static <T extends IBusManager> T busManager() {
        if (mBusManager != null) {
            return (T) mBusManager;
        }
        return (T) sMElleCityComponent.busManager();
    }

    /**
     * 配置图片加载模块
     */
    public static ImageConfig configureImage() {
        return sMElleCityComponent.imageConfig();
    }

    /**
     * 配置图片加载模块，用于替换默认的Glide
     *
     * @param imageManager 要替换Glide的图片加载管理者
     */
    public static ImageConfig configureImage(IImageManager imageManager) {
        mImageManager = imageManager;
        return sMElleCityComponent.imageConfig();
    }

    /**
     * 获取图片加载管理者
     */
    public static <T extends IImageManager> T imageManager() {
        if (mImageManager != null) {
            return (T) mImageManager;
        }
        return (T) sMElleCityComponent.imageManager();
    }

    /**
     * 配置缓存模块
     */
    public static CacheConfig configureCache() {
        return sMElleCityComponent.cacheConfig();
    }

    /**
     * 获取缓存管理者
     */
    public static CacheManager cacheManager() {
        return sMElleCityComponent.cacheManager();
    }

    /**
     * 配置网络请求模块
     */
    public static HttpConfig configureHttp() {
        return sMElleCityComponent.httpConfig();
    }

    /**
     * 获取网络请求管理者
     */
    public static HttpManager httpManager() {
        return sMElleCityComponent.httpManager();
    }

    public static ElleCityHttp ellecityHttp() {
        return sMElleCityComponent.ellecityHttp();
    }

    /**
     * 配置其他模块
     */
    public static OtherConfig configureOther() {
        return sMElleCityComponent.otherConfig();
    }

    public static ActivityListManager activityListManager() {
        return sMElleCityComponent.activityListManager();
    }

    /**
     * 获取权限管理者
     */
    public static PermissionManager permissionManager() {
        return sMElleCityComponent.permissionManager();
    }

    /**
     * 获取Application
     */
    public static Application application() {
        return sMElleCityComponent.application();
    }
}
