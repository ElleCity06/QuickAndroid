package com.ellecity06.quickjar.widget.irefresh.api;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 *  默认Footer创建器
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/11/28 15:29
 */
public interface DefaultRefreshFooterCreator {
    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout);
}
