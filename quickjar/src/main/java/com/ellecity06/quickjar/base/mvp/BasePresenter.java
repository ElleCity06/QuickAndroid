package com.ellecity06.quickjar.base.mvp;

/**
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/27 17:14
 */
public abstract class BasePresenter<V extends IBaseView,M extends IBaseModel>{

    protected V mIView;
    protected M mIModel;

    public BasePresenter(V iView, M iModel) {
        mIView = iView;
        mIModel = iModel;
    }

    public BasePresenter(V iView) {
        mIView = iView;
    }

    public BasePresenter() {
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        mIView = null;
    }
}
