package com.ellecity06.quickjar.image.support;

/**
 * 图片加载配置,通过这个配置临时的配置，加载中的图片，圆形圆角等等图片
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:36
 */

public class LoadOption {
    private int mLoadingResId;//加载中状态显示的图片
    private int mErrorResId;//加载失败状态显示的图片
    private boolean mIsShowTransition;//是否开启状态切换时的过渡动画
    private boolean mIsCircle;//是否加载为圆形图片
    private int mBorderWidth;//边框粗细，单位dp
    private int mBorderColor;//边框颜色
    private int mRoundRadius;//加载为圆角图片的圆角值
    private int mBlurRadius;//加载为模糊图片的模糊值
    private boolean mIsGray;//是否加载为灰白图片
    private boolean isGif;
    private int gifPlayCount = 1;

    public int getGifPlayCount() {
        return gifPlayCount;
    }

    public LoadOption setGifPlayCount(int gifPlayCount) {
        this.gifPlayCount = gifPlayCount;
        return this;
    }

    public boolean isGif() {
        return isGif;
    }

    public LoadOption setGif(boolean gif) {
        isGif = gif;
        return this;
    }

    public LoadOption() {
    }

    public LoadOption(int loadingResId, int errorResId) {
        mLoadingResId = loadingResId;
        mErrorResId = errorResId;
    }

    public int getLoadingResId() {
        return mLoadingResId;
    }

    public LoadOption setLoadingResId(int loadingResId) {
        this.mLoadingResId = loadingResId;
        return this;
    }

    public int getErrorResId() {
        return mErrorResId;
    }

    public LoadOption setErrorResId(int errorResId) {
        this.mErrorResId = errorResId;
        return this;
    }

    public boolean isShowTransition() {
        return mIsShowTransition;
    }

    public LoadOption setIsShowTransition(boolean isShowTransition) {
        this.mIsShowTransition = isShowTransition;
        return this;
    }

    public boolean isCircle() {
        return mIsCircle;
    }

    public LoadOption setIsCircle(boolean isCircle) {
        this.mIsCircle = isCircle;
        return this;
    }

    public int getRoundRadius() {
        return mRoundRadius;
    }

    public LoadOption setRoundRadius(int roundRadius) {
        this.mRoundRadius = roundRadius;
        return this;
    }

    public int getBlurRadius() {
        return mBlurRadius;
    }

    public LoadOption setBlurRadius(int blurRadius) {
        this.mBlurRadius = blurRadius;
        return this;
    }

    public boolean isGray() {
        return mIsGray;
    }

    public LoadOption setIsGray(boolean isGray) {
        this.mIsGray = isGray;
        return this;
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public LoadOption setBorderWidth(int borderWidth) {
        this.mBorderWidth = borderWidth;
        return this;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public LoadOption setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        return this;
    }
}
