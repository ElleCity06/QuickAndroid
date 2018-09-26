package com.ellecity06.quickjar.base.activity;

import android.app.Activity;
import android.os.Bundle;


/**
 * 想要通过本库的{@link ActivityLifeCallback }实现相关基类功能，那么你的Acticity必须实现此接口
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 16:49
 */
public interface IActivityLife {

    void onCreate(Activity activity, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(Bundle outState);

    void onDestroy();

}
