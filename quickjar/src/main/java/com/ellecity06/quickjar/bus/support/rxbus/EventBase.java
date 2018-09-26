package com.ellecity06.quickjar.bus.support.rxbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 事件处理的基类
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:03
 */
public class EventBase {
    protected final static Map<Class<?>, Object> STICKY_EVENT_MAP;
    protected final static Subject<Object> SUBJECT;

    static {
        SUBJECT = PublishSubject.create().toSerialized();
        STICKY_EVENT_MAP = new HashMap<>();
    }

    protected EventBase() {
    }

    static <T> Flowable<T> toFlowable(Class<T> eventType) {
        return SUBJECT.ofType(eventType).toFlowable(BackpressureStrategy.BUFFER);
    }

    /**
     * 删除指定粘性事件
     *
     * @param event
     */
    static synchronized void dellSticky(Object event) {
        if (!STICKY_EVENT_MAP.isEmpty()) {
            List<Class> classes = new ArrayList<>();
            for (Map.Entry<Class<?>, Object> objectEntry : STICKY_EVENT_MAP.entrySet()) {
                if (objectEntry.getKey() == event.getClass()) {
                    classes.add(event.getClass());
                }
            }
            stickyEventMapRemove(classes);
        }
    }

    static void stickyEventMapRemove(List<Class> classes) {
        for (Class aClass : classes)
            STICKY_EVENT_MAP.remove(aClass);
    }
}
