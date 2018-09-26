package com.ellecity06.quickjar.bus;

import com.ellecity06.quickjar.bus.support.IBusManager;
import com.ellecity06.quickjar.bus.support.rxbus.RxBus;



/**
 * RxBus管理者，库中默认使用此事件管理，通过QuickAndroid.busmanager来调用
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:15
 */
public class RxBusManager implements IBusManager {

    private RxBus mRxBus;

    public RxBusManager() {
        mRxBus = new RxBus();
    }

    @Override
    public void register(Object subscriber) {
        mRxBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        mRxBus.unregister(subscriber);
    }

    @Override
    public void postEvent(Object event) {
        mRxBus.post(event);
    }

    @Override
    public void postStickyEvent(Object event) {
        mRxBus.postSticky(event);
    }
}
