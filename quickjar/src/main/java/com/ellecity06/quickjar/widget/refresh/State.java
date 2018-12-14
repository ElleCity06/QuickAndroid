package com.ellecity06.quickjar.widget.refresh;

/**
 * 下拉刷新的状态
 *
 * @author ellecity06
 * @time 2018/7/25 16:29
 * @des TODO
 */
public enum State {
    /**
     * 重置
     */
    RESET,
    /**
     * 下拉状态
     */
    PULL,
    /**
     * 刷新中
     */
    REFRESHING,
    /**
     * 完成状态
     */
    COMPLETE
}
