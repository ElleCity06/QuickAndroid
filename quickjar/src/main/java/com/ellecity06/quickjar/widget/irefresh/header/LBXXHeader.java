package com.ellecity06.quickjar.widget.irefresh.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellecity06.quickjar.R;
import com.ellecity06.quickjar.widget.irefresh.api.RefreshHeader;
import com.ellecity06.quickjar.widget.irefresh.api.RefreshLayout;
import com.ellecity06.quickjar.widget.irefresh.constant.RefreshState;
import com.ellecity06.quickjar.widget.irefresh.constant.SpinnerStyle;
import com.ellecity06.quickjar.widget.irefresh.internal.InternalAbstract;

/**
 * 简单的一个刷新头部，用于展示如果定义
 *
 * @author ellecity06
 * @time 2018/11/28 11:06
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickjar.widget.irefresh.header
 * @des TODO
 */
public class LBXXHeader extends InternalAbstract implements RefreshHeader {

    private AnimationDrawable mAnimationDrawableable;
    private TextView mTv_refresh_text;
    private ImageView mIv_loading_refresh;
    private View mInflate;

    public LBXXHeader(Context context) {
        this(context, null);
    }

    protected LBXXHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected LBXXHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化动画
        mInflate = inflate(context, R.layout.ellecity_refresh_header, this);
        mIv_loading_refresh = mInflate.findViewById(R.id.iv_loading_refresh);
        mTv_refresh_text = mInflate.findViewById(R.id.tv_refresh_text);
        if (mIv_loading_refresh.getDrawable() != null) {
            mAnimationDrawableable = (AnimationDrawable) mIv_loading_refresh.getDrawable();
        }

    }

    @NonNull
    @Override
    public View getView() {
        return mInflate;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        if (mAnimationDrawableable != null) {
            //            mIv_loading_refresh.setImageDrawable(mAnimationDrawableable);
            mAnimationDrawableable.start();
        }

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (success) {
            mTv_refresh_text.setText("刷新完成");
            //            mIv_loading_refresh.setImageResource(R.drawable.loading_loadcomplete);
        } else {
            mTv_refresh_text.setText("刷新失败");
        }
        if (mAnimationDrawableable != null) {

            mAnimationDrawableable.stop();
        }

        return 300;//延迟300毫秒之后再弹回
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mTv_refresh_text.setText("下拉开始刷新");
                if (mAnimationDrawableable != null && mAnimationDrawableable.isRunning()) {
                    mAnimationDrawableable.stop();
                }
                break;
            case Refreshing:
                mTv_refresh_text.setText("正在加载...");
                if (mAnimationDrawableable != null) {
                    mAnimationDrawableable.start();
                }
                break;
            case ReleaseToRefresh:
                mTv_refresh_text.setText("释放立即刷新");
                if (mAnimationDrawableable != null && mAnimationDrawableable.isRunning()) {
                    mAnimationDrawableable.stop();
                }
                break;
        }
    }

}
