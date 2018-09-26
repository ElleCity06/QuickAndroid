package com.ellecity06.quickjar.http2.support.observer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ellecity06.quickjar.http2.support.body.ProgressListener;
import com.ellecity06.quickjar.http2.support.throwable.HttpThrowable;
import com.ellecity06.quickjar.http2.support.throwable.ThrowableHandler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 文件上传的请求回调（包含上传进度回调）
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:30
 */
public abstract class UploadObserver<T> implements Observer<T>, ProgressListener {

    private String mUploadUrl;
    private String mQualifier;

    /**
     * 如果不需要监听进度，则使用此构造函数
     */
    public UploadObserver() {
    }

    /**
     * 如果是普通地监听某个上传的进度，则使用此构造函数
     *
     * @param uploadUrl 上传的URL地址
     */
    public UploadObserver(@NonNull String uploadUrl) {
        this.mUploadUrl = uploadUrl;
    }

    /**
     * 如果是使用同一个URL但根据请求参数的不同而上传不同资源的情况，则使用此构造函数
     *
     * @param uploadUrl 上传的URL地址
     * @param qualifier 用以区分的字符串
     */
    public UploadObserver(@NonNull String uploadUrl, @Nullable String qualifier) {
        this.mUploadUrl = uploadUrl;
        this.mQualifier = qualifier;
    }

    public String getUploadUrl() {
        return mUploadUrl;
    }

    public String getQualifier() {
        return mQualifier;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        onResult(t);
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof Exception) {
            onError(0, ThrowableHandler.handleThrowable(throwable));
        } else {
            onError(0, new HttpThrowable(HttpThrowable.UNKNOWN, "未知错误", throwable));
        }
    }

    @Override
    public void onProgressError(long id, Exception e) {
        onError(id, ThrowableHandler.handleThrowable(e));
    }

    public abstract void onResult(T result);

    //如果progressInfoId为0，则为请求相关的异常，如果不为0，则为上传读写过程的异常
    public abstract void onError(long progressInfoId, HttpThrowable httpThrowable);

}
