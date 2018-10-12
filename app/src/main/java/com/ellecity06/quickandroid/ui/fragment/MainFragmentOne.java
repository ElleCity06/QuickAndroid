package com.ellecity06.quickandroid.ui.fragment;

import com.ellecity06.quickandroid.R;
import com.ellecity06.quickandroid.base.BaseFragment;

/**
 * @author ellecity06
 * @time 2018/9/29 11:13
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.ui.fragment
 * @des TODO
 */
public class MainFragmentOne extends BaseFragment{
    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
