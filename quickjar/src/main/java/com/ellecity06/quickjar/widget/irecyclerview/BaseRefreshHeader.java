package com.ellecity06.quickjar.widget.irecyclerview;

/**
 * ElleCityRecyclerView 的刷新头部
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/10/17 15:39
 */
interface BaseRefreshHeader {
    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplate();

    int getVisiableHeight();
}
