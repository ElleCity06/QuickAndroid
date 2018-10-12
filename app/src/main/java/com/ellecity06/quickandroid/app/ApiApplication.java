package com.ellecity06.quickandroid.app;

import android.app.Application;

import com.ellecity06.quickjar.QuickAndroid;

/**
 * @author ellecity06
 * @time 2018/8/31 18:55
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid
 * @des TODO
 */
public class ApiApplication extends Application {


    private static ApiApplication mApiApplication;


    public static ApiApplication getApiApplication() {
        return mApiApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApiApplication = this;
        QuickAndroid.init(this);

        QuickAndroid.create();

    }
}

