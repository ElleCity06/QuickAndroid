package com.ellecity06.quickjar.http2.support.observer;


import com.ellecity06.quickjar.http2.support.throwable.HttpThrowable;
import com.ellecity06.quickjar.http2.support.throwable.ThrowableHandler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 普通的api请求回调
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:29
 */
public abstract class CommonObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof Exception) {
            onError(ThrowableHandler.handleThrowable(throwable));
        } else {
            onError(new HttpThrowable(HttpThrowable.UNKNOWN, "未知错误", throwable));
        }
    }

    @Override
    public void onNext(T t) {
        onResult(t);
    }

    public abstract void onResult(T result);

    public abstract void onError(HttpThrowable httpThrowable);
}
