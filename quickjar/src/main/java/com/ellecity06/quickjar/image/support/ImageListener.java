package com.ellecity06.quickjar.image.support;

/**
 * 图片回调，用于获取bitmap或下载图片
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:34
 */

public interface ImageListener<T> {

    void onSuccess(T result);

    void onFail(Throwable throwable);
}
