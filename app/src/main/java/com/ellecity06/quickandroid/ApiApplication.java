package com.ellecity06.quickandroid;

import android.app.Application;

import com.ellecity06.quickandroid.demo.component.ACompoment;
import com.ellecity06.quickandroid.demo.component.ApplicationComponent;
import com.ellecity06.quickandroid.demo.component.DaggerApplicationComponent;
import com.ellecity06.quickandroid.demo.module.AModule;
import com.ellecity06.quickjar.QuickAndroid;

/**
 * @author ellecity06
 * @time 2018/8/31 18:55
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid
 * @des TODO
 */
public class ApiApplication extends Application {

    private ApplicationComponent mComponent;
    private static ApiApplication mApiApplication;
    private  static ACompoment sACompoment;

    public static ApiApplication getApiApplication() {
        return mApiApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApiApplication = this;
        QuickAndroid.init(this);

        QuickAndroid.create();

        mComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public static ACompoment getACompoment() {
        if (sACompoment==null){
            sACompoment = mApiApplication.getComponent().plus(new AModule());
        }
        return sACompoment;
    }
}
