package com.ellecity06.quickjar.widget.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellecity06.quickjar.R;


/**
 * @author ellecity06
 * @time 2018/7/25 15:56
 * @des 刷新的头部
 */

public class ElleCityRefreshHeader extends FrameLayout implements IRefreshHeader {

    private AnimationDrawable mAnimationDrawableable;
    private TextView mTv_refresh_text;
    private ImageView mIv_loading_refresh;

    public ElleCityRefreshHeader(@NonNull Context context) {
        this(context, null);
    }

    public ElleCityRefreshHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        // 初始化动画
        inflate(context, R.layout.ellecity_refresh_header, this);
        mIv_loading_refresh = findViewById(R.id.iv_loading_refresh);
        mTv_refresh_text = findViewById(R.id.tv_refresh_text);
        if (mIv_loading_refresh.getDrawable() != null) {
            mAnimationDrawableable = (AnimationDrawable) mIv_loading_refresh.getDrawable();
        }


    }

    /**
     * 初始化的状态
     */
    @Override
    public void reset() {
        mTv_refresh_text.setText("下拉刷新");
        if (mAnimationDrawableable != null)
            mIv_loading_refresh.setImageDrawable(mAnimationDrawableable);
        //        mIv_loading_refresh.setImageResource(R.drawable.loading_refresh);

    }

    @Override
    public void pull() {

    }

    /**
     * 正在刷新的状态
     */
    @Override
    public void refreshing() {

        mTv_refresh_text.setText("正在加载...");
        if (mAnimationDrawableable != null) {

            mAnimationDrawableable.start();
        }

    }

    /**
     * @param currentPos
     * @param lastPos
     * @param refreshPos
     * @param isTouch    手指是否按下状态（通过scroll自动滚动时需要判断）
     * @param state      当前状态
     */
    @Override
    public void onPositionChange(float currentPos, float lastPos, float refreshPos, boolean isTouch, State state) {
        // 往上拉
        if (currentPos < refreshPos && lastPos >= refreshPos) {
            Log.i("ElleCityRefreshHeader", ">>>>up");
            if (isTouch && state == State.PULL) {
                mTv_refresh_text.setText("下拉刷新");
                if (mAnimationDrawableable != null) {
                    mAnimationDrawableable.stop();
                }
            }
            // 往下拉
        } else if (currentPos > refreshPos && lastPos <= refreshPos) {
            Log.i("ElleCityRefreshHeader", ">>>>down");
            if (isTouch && state == State.PULL) {
                mTv_refresh_text.setText("释放立即刷新");
                //                mIv_loading_refresh.setImageResource(R.drawable.loading_refresh);
                if (mAnimationDrawableable != null) {

                    mAnimationDrawableable.start();
                }
            }
        }
    }

    /**
     * 刷新完成
     */
    @Override
    public void complete() {
        mTv_refresh_text.setText("刷新完成");
        if (mAnimationDrawableable != null) {

            mAnimationDrawableable.stop();
        }
        mIv_loading_refresh.setImageResource(R.drawable.loading_loadcomplete);

    }
}
