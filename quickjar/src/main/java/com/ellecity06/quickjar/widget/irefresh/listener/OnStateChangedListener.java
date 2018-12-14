package com.ellecity06.quickjar.widget.irefresh.listener;


import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.ellecity06.quickjar.widget.irefresh.api.RefreshLayout;
import com.ellecity06.quickjar.widget.irefresh.constant.RefreshState;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;
import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static android.support.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * 刷新状态改变监听
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/11/28 15:56
 */

public interface OnStateChangedListener {
    /**
     * 状态改变事件 {@link RefreshState}
     *
     * @param refreshLayout RefreshLayout
     * @param oldState      改变之前的状态
     * @param newState      改变之后的状态
     */
    @RestrictTo({LIBRARY, LIBRARY_GROUP, SUBCLASSES})
    void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState);
}
