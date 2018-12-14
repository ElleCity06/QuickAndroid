package com.ellecity06.quickjar.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ellecity06.quickjar.R;

/**
 * @author ellecity06
 * @time 2018/10/17 14:14
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickjar.widget
 * @des TODO
 */
public class RecyclerViewLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.layout_loadmore;
    }
    @Override
    protected int getLoadingViewId() {
        return R.id.item_loading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.item_loadfail;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.item_loadcomplete;
    }
}
