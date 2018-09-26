package com.ellecity06.quickjar.http2.support.observer;


import com.ellecity06.quickjar.http2.support.body.ProgressListener;
import com.ellecity06.quickjar.http2.support.throwable.HttpThrowable;
import com.ellecity06.quickjar.http2.support.throwable.ThrowableHandler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 文件下载的请求回调（包含下载进度回调）
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:30
 */

public abstract class DownloadObserver implements Observer<ResponseBody>, ProgressListener {

    private boolean mIsFileSaveSuccess;//文件是否成功保存到本地
    private String mFilePath;//保存的文件的绝对地址

    private String mDownloadUrl;
    private String mQualifier;

    /**
     * 如果不需要监听进度，则使用此构造函数
     */
    public DownloadObserver() {
    }

    /**
     * 如果是普通地监听某个下载的进度，则使用此构造函数
     *
     * @param downloadUrl 下载的URL地址
     */
    public DownloadObserver(String downloadUrl) {
        this.mDownloadUrl = downloadUrl;
    }

    /**
     * 如果是使用同一个URL但根据请求参数的不同而下载不同资源的情况，则使用此构造函数
     *
     * @param downloadUrl 下载的URL地址
     * @param qualifier   用以区分的字符串
     */
    public DownloadObserver(String downloadUrl, String qualifier) {
        this.mDownloadUrl = downloadUrl;
        this.mQualifier = qualifier;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
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

    @Override
    public void onNext(ResponseBody responseBody) {
        onResult(mIsFileSaveSuccess, mFilePath);
    }

    public void setResult(boolean isFileSaveSuccess, String filePath) {
        mIsFileSaveSuccess = isFileSaveSuccess;
        mFilePath = filePath;
    }

    public abstract void onResult(boolean isSaveSuccess, String filePath);

    //如果progressInfoId为0，则为请求相关的异常，如果不为0，则为下载读写过程的异常
    public abstract void onError(long progressInfoId, HttpThrowable httpThrowable);

}
