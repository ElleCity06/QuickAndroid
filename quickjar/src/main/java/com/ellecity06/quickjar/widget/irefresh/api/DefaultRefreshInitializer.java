package com.ellecity06.quickjar.widget.irefresh.api;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 默认全局初始化器
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/11/28 15:30
 */
public interface DefaultRefreshInitializer {
    void initialize(@NonNull Context context, @NonNull RefreshLayout layout);
}
