package com.ellecity06.quickjar.widget.loadlayout;

/**
 * 布局的状态
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/10/15 9:57
 */
public interface State {
    /**
     * 加载中
     */
    int LOADING = 1;

    /**
     * 加载成功
     */
    int SUCCESS = 2;

    /**
     * 加载失败
     */
    int FAILED = 3;

    /**
     * 加载成功且返回无数据
     */
    int NO_DATA = 4;

}
