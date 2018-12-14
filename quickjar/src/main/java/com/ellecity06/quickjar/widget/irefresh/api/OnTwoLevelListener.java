package com.ellecity06.quickjar.widget.irefresh.api;

import android.support.annotation.NonNull;

/**
 * 二级刷新监听器
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/11/28 15:31
 */
public interface OnTwoLevelListener {
    /**
     * 二级刷新触发
     *
     * @param refreshLayout 刷新布局
     * @return true 将会展开二楼状态 false 关闭刷新
     */
    boolean onTwoLevel(@NonNull RefreshLayout refreshLayout);
}