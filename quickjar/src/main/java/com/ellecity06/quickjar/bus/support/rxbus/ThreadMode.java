package com.ellecity06.quickjar.bus.support.rxbus;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 定义事件线程
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:07
 */
public enum ThreadMode {
    MAIN_THREAD,
    NEW_THREAD,
    IO,
    SINGLE,
    COMPUTATION,
    TRAMPOLINE;

    public static Scheduler getScheduler(ThreadMode thread) {
        Scheduler scheduler;
        switch (thread) {
            case MAIN_THREAD:
                scheduler = AndroidSchedulers.mainThread();
                break;
            case NEW_THREAD:
                scheduler = Schedulers.newThread();
                break;
            case IO:
                scheduler = Schedulers.io();
                break;
            case SINGLE:
                scheduler = Schedulers.single();
                break;
            case COMPUTATION:
                scheduler = Schedulers.computation();
                break;
            case TRAMPOLINE:
                scheduler = Schedulers.trampoline();
                break;
            default:
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        return scheduler;
    }
}
