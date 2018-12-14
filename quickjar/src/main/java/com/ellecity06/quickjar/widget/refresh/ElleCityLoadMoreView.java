package com.ellecity06.quickjar.widget.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellecity06.quickjar.R;


/**
 * @author ellecity06
 * @time 2018/7/25 16:04
 * @des 加载更多部分
 */
public class ElleCityLoadMoreView extends FrameLayout implements ILoadMoreView {

    private TextView tvHitText;
    private View view;
    private ImageView mIv_loadmore;
    private AnimationDrawable mAnimationDrawable;

    public ElleCityLoadMoreView(Context context) {
        this(context, null);
    }

    public ElleCityLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = inflate(context, R.layout.ellecity_load_more, this);
        tvHitText = (TextView) view.findViewById(R.id.tv_hit_content);
        mIv_loadmore = findViewById(R.id.iv_load_more);
        if (mIv_loadmore.getDrawable() != null) {
            mAnimationDrawable = (AnimationDrawable) mIv_loadmore.getDrawable();
        }

    }


    @Override
    public void reset() {
        mIv_loadmore.setVisibility(INVISIBLE);
        tvHitText.setVisibility(INVISIBLE);
        tvHitText.setText("正在加载...");
        if (mAnimationDrawable != null) {
            mIv_loadmore.setImageDrawable(mAnimationDrawable);
        }
    }

    @Override
    public void loading() {
        mIv_loadmore.setVisibility(VISIBLE);
        tvHitText.setVisibility(VISIBLE);

        if (mAnimationDrawable != null) {
            mIv_loadmore.setImageDrawable(mAnimationDrawable);
            mAnimationDrawable.start();
        }
        tvHitText.setText("正在加载...");
    }

    @Override
    public void loadComplete() {
        mIv_loadmore.setVisibility(VISIBLE);
        tvHitText.setVisibility(VISIBLE);
        if (mAnimationDrawable != null) {

            mAnimationDrawable.stop();
        }
        mIv_loadmore.setImageResource(R.drawable.loading_loadcomplete);
        tvHitText.setText("加载完成");

    }

    @Override
    public void loadFail() {
        mIv_loadmore.setVisibility(VISIBLE);
        tvHitText.setVisibility(VISIBLE);
        if (mAnimationDrawable != null) {

            mAnimationDrawable.stop();
        }
        mIv_loadmore.setImageResource(R.drawable.loading_fial_more);
        tvHitText.setText("加载失败,点击重新加载");

    }

    @Override
    public void loadNothing() {
        mIv_loadmore.setVisibility(INVISIBLE);
        tvHitText.setVisibility(VISIBLE);
        if (mAnimationDrawable != null) {

            mAnimationDrawable.stop();
        }
        tvHitText.setText("没有更多可以加载");
    }

    @Override
    public View getCanClickFailView() {
        return view;
    }


}
