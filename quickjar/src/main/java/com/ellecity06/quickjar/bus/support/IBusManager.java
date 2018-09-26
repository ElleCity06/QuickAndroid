package com.ellecity06.quickjar.bus.support;

import com.ellecity06.quickjar.bus.EventBusManager;
import com.ellecity06.quickjar.bus.RxBusManager;

/**
 * 事件总线接口，通过实现此接口自定义自己的事件管理，目前此库提供了两种事件管理者 ，{@link EventBusManager}和{@link RxBusManager}
 * 可以自行配置选择
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:08
 */

public interface IBusManager {

    void register(Object subscriber);//订阅

    void unregister(Object subscriber);//解除订阅

    void postEvent(Object event);//发送事件

    void postStickyEvent(Object event);//发送粘性事件
}
