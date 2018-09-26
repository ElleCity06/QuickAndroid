package com.ellecity06.quickjar.http2.support.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Header拦截器
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:22
 */

public class HttpHeaderInterceptor implements Interceptor {

    private Map<String, String> mMapHeader;

    public HttpHeaderInterceptor(Map<String, String> mapHeader) {
        this.mMapHeader = mapHeader;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        for (Map.Entry<String, String> entry : mMapHeader.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
