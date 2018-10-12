package com.ellecity06.quickandroid.widget.refresh;

import android.view.View;

/**
 * @author ellecity06
 * @time 2018/7/25 17:04
 * @des TODO
 */
public interface ILoadMoreView {
    /**
     * 重置
     */
    void reset();

    /**
     * 加载中
     */
    void loading();

    /**
     * 加载完成
     */
    void loadComplete();

    void loadFail();

    void loadNothing();

    View getCanClickFailView();


}
