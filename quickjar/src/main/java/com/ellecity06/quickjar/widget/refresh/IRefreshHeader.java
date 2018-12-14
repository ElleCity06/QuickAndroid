package com.ellecity06.quickjar.widget.refresh;

/**
 * 下拉刷新的头部接口，自定义头部需实现这个接口
 *
 * @author ellecity06
 * @time 2018/7/25 16:03
 * @des TODO
 */
public interface IRefreshHeader {
    /**
     * 松手，头部隐藏后会回调这个方法
     */
    void reset();

    /**
     * 下拉出头部的一瞬间调用
     */
    void pull();

    /**
     * 正在刷新的时候调用
     */
    void refreshing();

    /**
     * 头部滚动的时候持续调用
     *
     * @param currentOffsetdistance    target当前偏移高度
     * @param lastOffsetDistance       target上一次的偏移高度
     * @param canRefreshOffsetDistance 可以松手刷新的高度
     * @param isTouch                  手指是否按下状态（通过scroll自动滚动时需要判断）
     * @param state                    当前状态
     */
    void onPositionChange(float currentOffsetdistance, float lastOffsetDistance, float canRefreshOffsetDistance, boolean isTouch, State state);

    /**
     * 刷新成功的时候调用
     */
    void complete();
}

