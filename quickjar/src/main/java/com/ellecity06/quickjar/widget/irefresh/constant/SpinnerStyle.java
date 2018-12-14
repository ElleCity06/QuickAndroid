package com.ellecity06.quickjar.widget.irefresh.constant;

/**
 * 顶部和底部的组件在拖动时候的变换方式
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/11/28 15:42
 */

public enum SpinnerStyle {
    Translate,//平行移动        特点: HeaderView高度不会改变，
    Scale,//拉伸形变            特点：在下拉和上弹（HeaderView高度改变）时候，会自动触发OnDraw事件
    FixedBehind,//固定在背后    特点：HeaderView高度不会改变，
    FixedFront,//固定在前面     特点：HeaderView高度不会改变，
    MatchLayout//填满布局       特点：HeaderView高度不会改变，尺寸充满 RefreshLayout
}
