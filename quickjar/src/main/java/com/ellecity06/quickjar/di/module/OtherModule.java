package com.ellecity06.quickjar.di.module;


import android.support.v4.util.SimpleArrayMap;

import com.ellecity06.quickjar.base.activity.ActivityLife;
import com.ellecity06.quickjar.base.activity.IActivityLife;
import com.ellecity06.quickjar.base.fragment.FragmentLife;
import com.ellecity06.quickjar.base.fragment.IFragmentLife;
import com.ellecity06.quickjar.other.ActivityListManager;
import com.ellecity06.quickjar.other.CrashDiary;
import com.ellecity06.quickjar.other.PermissionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * 其他模块（Activity栈管理，RxPermission，崩溃日志输出，activity/fragment基类）的供应Module
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:35
 */

@Module
public class OtherModule {

    @Singleton
    @Provides
    ActivityListManager activityListManager() {
        return new ActivityListManager();
    }

    @Singleton
    @Provides
    PermissionManager permissionManager() {
        return new PermissionManager();
    }

    @Singleton
    @Provides
    CrashDiary crashDiary() {
        return new CrashDiary();
    }

    @Singleton
    @Provides
    SimpleArrayMap<String, IActivityLife> iActivityLifes() {
        return new SimpleArrayMap<>();
    }

    @Provides
    IActivityLife iActivityLife() {
        return new ActivityLife();
    }

    @Singleton
    @Provides
    SimpleArrayMap<String, IFragmentLife> iFragmentLifes() {
        return new SimpleArrayMap<>();
    }

    @Provides
    IFragmentLife iFragmentLife() {
        return new FragmentLife();
    }


}

