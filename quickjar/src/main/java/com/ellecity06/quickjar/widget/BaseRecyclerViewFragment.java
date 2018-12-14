package com.ellecity06.quickjar.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ellecity06.quickjar.R;
import com.ellecity06.quickjar.base.fragment.IBaseFragment;
import com.ellecity06.quickjar.base.mvp.BasePresenter;
import com.ellecity06.quickjar.util.Preconditions;
import com.ellecity06.quickjar.widget.loadlayout.LoadLayout;
import com.ellecity06.quickjar.widget.loadlayout.OnLoadListener;
import com.ellecity06.quickjar.widget.loadlayout.State;
import com.ellecity06.quickjar.widget.refresh.ElleCityRefreshLayout;
import com.ellecity06.quickjar.widget.refresh.LoadModel;

import java.util.List;

import javax.inject.Inject;

/**
 * 简单的一个列表样式Fragment 内层使用的是RecyclerView，只需要实现提供数据和布局就可以实现一个基础的列表。
 *
 * @author ellecity06
 * @time 2018/10/15 10:41
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickjar.widget 下一站茶山刘--房东的猫
 * @des
 */
public abstract class BaseRecyclerViewFragment<P extends BasePresenter> extends Fragment implements IBaseFragment, BaseQuickAdapter.RequestLoadMoreListener {

    protected Activity mActivity;
    //根布局视图
    private View mContentView;
    //视图是否已经初始化完毕
    private boolean isViewReady;
    //fragment是否处于可见状态
    private boolean isFragmentVisible;
    //是否已经初始化加载过
    protected boolean isLoaded;
    @Inject
    @Nullable
    protected P mPresenter;
    public RecyclerView mBase_recyclerview;
    public ElleCityRefreshLayout mBase_refresh;
    public LoadLayout mBase_loadlayout;
    public BaseQuickAdapter mBaseQuickAdapter;

    /**
     * pageNo 默认为 1
     */
    public int mPageNo = 1;
    /**
     * pageSize 默认为 10
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 下拉刷新中
     */
    public boolean inPullDownProcess;
    /**
     * 上拉加载中
     */
    public boolean inPullUpProcess;

    /**
     * 是否使用懒加载 返回true的时候表示只有在Fragment可见的时候才进行初始化加载数据（建议返回true）
     *
     * @return
     */
    protected abstract boolean isLazyLoad();

    /**
     * 获取数据
     */
    protected abstract void addContentRequest();

    /**
     * 实现你的adapter
     */
    public abstract void initAdapter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mContentView == null) {
            try {
                mContentView = inflater.inflate(getContentLayout(), container, false);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }

            Preconditions.checkNotNull(mContentView, "根布局的id非法导致根布局为空,请检查后重试!");

        }
        return mContentView;
    }

    private int getContentLayout() {
        return R.layout.fragment_base_recyclerview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //视图准备完毕
        isViewReady = true;
        if (!isLazyLoad() || isFragmentVisible) {
            init();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isFragmentVisible = isVisibleToUser;
        //如果视图准备完毕且Fragment处于可见状态，则开始初始化操作
        if (isLazyLoad() && isViewReady && isFragmentVisible) {
            init();
        }
    }

    private void init() {
        if (!isLoaded) {
            isLoaded = true;
            initView();
            initData();
            initEvent();
        }
    }

    private void initData() {
        //设置“加载”状态时要做的事情
        mBase_loadlayout.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                onRefresh();
            }
        });
        //设置页面为“加载”状态
        mBase_loadlayout.setLayoutState(State.LOADING);

    }

    private void initEvent() {
        mBase_refresh.addEasyEvent(new ElleCityRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                mPageNo = 1;

                inPullDownProcess = true;
                onRefresh();
            }
        });
        mBase_refresh.setLoadMoreModel(LoadModel.NONE);
    }

    private void onRefresh() {
        if (mBaseQuickAdapter != null) {
            mBaseQuickAdapter.setEnableLoadMore(false);
            mBaseQuickAdapter.setOnLoadMoreListener(this, mBase_recyclerview);
            mBaseQuickAdapter.setLoadMoreView(new RecyclerViewLoadMoreView());
            if (inPullDownProcess) {
                addContentRequest();
                return;
            }
            // TODO 正在刷新，显示正在加载的视图
            //            baseQuickAdapter.setEmptyView(mLoadingView);
            addContentRequest();

        }
    }


    /**
     * 初始化视图
     */
    private void initView() {
        //
        mBase_recyclerview = mContentView.findViewById(R.id.base_recyclerview);
        mBase_refresh = mContentView.findViewById(R.id.base_refresh);
        mBase_loadlayout = mContentView.findViewById(R.id.base_loadlayout);
        mBase_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter();

    }


    @Override
    public void onSaveState(Bundle bundleToSave) {

    }

    @Override
    public void onRestoreState(Bundle bundleToRestore) {

    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife解绑
        isViewReady = false;
        isLoaded = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }

    /**
     * 加载更多的监听 // TODO
     */
    @Override
    public void onLoadMoreRequested() {
        mBase_refresh.setEnablePullToRefresh(false);

        if (mBaseQuickAdapter.getData() != null) {
            if (mBaseQuickAdapter.getData().size() < PAGE_SIZE) {
                mBaseQuickAdapter.loadMoreEnd();

            } else {
                //            baseQuickAdapter.loadMoreEnd(false);
                inPullUpProcess = true;


                addContentRequest();


            }
        } else {
            mBaseQuickAdapter.loadMoreFail();
        }
        mBase_refresh.setEnablePullToRefresh(true);
    }

    /**
     * 对返回结果做统一处理，也可以自行处理
     *
     * @param list
     */
    public void commonProcess(List<?> list) {
        if (inPullDownProcess) {//下拉
            inPullDownProcess = false;
            if (list != null && list.size() > 0) {
                mBaseQuickAdapter.setNewData(list);
                if (list.size() < PAGE_SIZE) {
                    mBaseQuickAdapter.loadMoreEnd();
                } else {
                    mBaseQuickAdapter.setEnableLoadMore(true);
                }
            } else {
                mBaseQuickAdapter.setNewData(null);//不清空原先数据 不显示空视图
                onLoadNoData();
            }
            stopRefresh();
            return;
        }
        if (inPullUpProcess) {//上拉
            inPullUpProcess = false;
            if (list != null && list.size() > 0) {
                mBaseQuickAdapter.addData(list);
                mBaseQuickAdapter.loadMoreComplete();
                mBaseQuickAdapter.setEnableLoadMore(true);
            } else {
                mBaseQuickAdapter.loadMoreEnd();
                //                baseQuickAdapter.setEnableLoadMore(false);
            }
            return;
        }

        if (list != null && list.size() > 0) {//正常加载
            mBaseQuickAdapter.addData(list);
            if (list.size() < PAGE_SIZE) {
                mBaseQuickAdapter.loadMoreEnd();
            } else {
                mBaseQuickAdapter.setEnableLoadMore(true);
            }
        } else {
            onLoadNoData();
        }
    }

    /**
     * 停止刷新
     */
    public void stopRefresh() {
        if (mBase_refresh != null) {
            mBase_refresh.refreshComplete();
        }
    }

    /**
     * 加载失败的情况
     */
    public void onLoadFail() {
        if (mBase_loadlayout != null) {
            mBase_loadlayout.setLayoutState(State.FAILED);
        }
    }

    /**
     * 加载没数据的情况
     */
    public void onLoadNoData() {
        if (mBase_loadlayout != null) {
            mBase_loadlayout.setLayoutState(State.NO_DATA);
        }
    }

}
