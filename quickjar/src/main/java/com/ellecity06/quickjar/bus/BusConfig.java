package com.ellecity06.quickjar.bus;

import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/**
 * 事件总线的配置器
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:13
 */

public class BusConfig {

    private boolean mIsUseIndex;
    private SubscriberInfoIndex mIndex;

    //设置用于加速的Index
    public BusConfig setIndex(SubscriberInfoIndex index) {
        mIndex = index;
        return this;
    }

    //设置是否使用index进行加速
    public BusConfig setIsUseIndex(boolean isUseIndex) {
        mIsUseIndex = isUseIndex;
        return this;
    }

    protected SubscriberInfoIndex getIndex() {
        return mIndex;
    }

    protected boolean isUseIndex() {
        return mIsUseIndex;
    }
}
