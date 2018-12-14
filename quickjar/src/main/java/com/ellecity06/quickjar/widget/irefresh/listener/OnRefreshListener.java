package com.ellecity06.quickjar.widget.irefresh.listener;

import android.support.annotation.NonNull;

import com.ellecity06.quickjar.widget.irefresh.api.RefreshLayout;

/**
 * 基本的刷新监听
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/11/28 15:55
 */

public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
