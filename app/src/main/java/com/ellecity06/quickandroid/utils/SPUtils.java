package com.ellecity06.quickandroid.utils;

import com.ellecity06.quickandroid.app.Constants;
import com.ellecity06.quickjar.QuickAndroid;

/**
 * 用于存取保存的key-values
 *
 * @author ellecity06
 * @time 2018/10/9 15:10
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.utils
 * @des
 */
public class SPUtils {


    /**
     * 获取当前是否是夜间模式
     *
     * @return
     */
    public static boolean getNightMode() {
        return QuickAndroid.cacheManager().spCache().getBoolean(Constants.KEY_MODE_NIGHT, false);
    }

    /**
     * 存储当前夜间模式
     *
     * @param nightMode
     */
    public static void setNightMode(boolean nightMode) {
        QuickAndroid.cacheManager().spCache().put(Constants.KEY_MODE_NIGHT, nightMode);
    }
}
