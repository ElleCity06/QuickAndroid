/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ellecity06.quickjar.http.request;


import com.ellecity06.quickjar.http.cache.model.CacheResult;
import com.ellecity06.quickjar.http.callback.CallBack;
import com.ellecity06.quickjar.http.callback.CallBackProxy;
import com.ellecity06.quickjar.http.func.ApiResultFunc;
import com.ellecity06.quickjar.http.func.CacheResultFunc;
import com.ellecity06.quickjar.http.func.HandleFuc;
import com.ellecity06.quickjar.http.func.RetryExceptionFunc;
import com.ellecity06.quickjar.http.model.ApiResult;
import com.ellecity06.quickjar.http.subsciber.CallBackSubsciber;
import com.ellecity06.quickjar.http.transformer.HandleErrTransformer;
import com.ellecity06.quickjar.http.utils.RxUtil;
import com.ellecity06.quickjar.http.utils.Utils;
import com.google.gson.reflect.TypeToken;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 自定义请求，例如你有自己的ApiService
 * @author ellecity06
 * @time 2018/7/17 14:17
 * @des
 */
@SuppressWarnings(value={"unchecked", "deprecation"})
public class CustomRequest extends BaseRequest<CustomRequest> {
    public CustomRequest() {
        super("");
    }

    @Override
    public CustomRequest build() {
        return super.build();
    }

    /**
     * 创建api服务  可以支持自定义的api，默认使用BaseApiService,上层不用关心
     *
     * @param service 自定义的apiservice class
     */
    public <T> T create(final Class<T> service) {
        checkvalidate();
        return retrofit.create(service);
    }

    private void checkvalidate() {
        Utils.checkNotNull(retrofit, "请先在调用build()才能使用");
    }

    /**
     * 调用call返回一个Observable<T>
     * 举例：如果你给的是一个Observable<ApiResult<AuthModel>> 那么返回的<T>是一个ApiResult<AuthModel>
     */
    public <T> Observable<T> call(Observable<T> observable) {
        checkvalidate();
        return observable.compose(RxUtil.io_main())
                .compose(new HandleErrTransformer())
                .retryWhen(new RetryExceptionFunc(retryCount, retryDelay, retryIncreaseDelay));
    }

    public <T> void call(Observable<T> observable, CallBack<T> callBack) {
        call(observable, new CallBackSubsciber(context, callBack));
    }

    public <R> void call(Observable observable, Observer<R> subscriber) {
        observable.compose(RxUtil.io_main())
                .subscribe(subscriber);
    }


    /**
     * 调用call返回一个Observable,针对ApiResult的业务<T>
     * 举例：如果你给的是一个Observable<ApiResult<AuthModel>> 那么返回的<T>是AuthModel
     */
    public <T> Observable<T> apiCall(Observable<ApiResult<T>> observable) {
        checkvalidate();
        return observable
                .map(new HandleFuc<T>())
                .compose(RxUtil.<T>io_main())
                .compose(new HandleErrTransformer<T>())
                .retryWhen(new RetryExceptionFunc(retryCount, retryDelay, retryIncreaseDelay));
    }

    public <T> Disposable apiCall(Observable<T> observable, CallBack<T> callBack) {
        return call(observable, new CallBackProxy<ApiResult<T>, T>(callBack) {
        });
    }

    public <T> Disposable call(Observable<T> observable, CallBackProxy<? extends ApiResult<T>, T> proxy) {
        Observable<CacheResult<T>> cacheobservable = build().toObservable(observable, proxy);
        if (CacheResult.class != proxy.getCallBack().getRawType()) {
            return cacheobservable.compose(new ObservableTransformer<CacheResult<T>, T>() {
                @Override
                public ObservableSource<T> apply(@NonNull Observable<CacheResult<T>> upstream) {
                    return upstream.map(new CacheResultFunc<T>());
                }
            }).subscribeWith(new CallBackSubsciber<T>(context, proxy.getCallBack()));
        } else {
            return cacheobservable.subscribeWith(new CallBackSubsciber<CacheResult<T>>(context, proxy.getCallBack()));
        }
    }

    @SuppressWarnings(value={"unchecked", "deprecation"})
    private <T> Observable<CacheResult<T>> toObservable(Observable observable, CallBackProxy<? extends ApiResult<T>, T> proxy) {
        return observable.map(new ApiResultFunc(proxy != null ? proxy.getType() : new TypeToken<ResponseBody>() {
        }.getType()))
                .compose(isSyncRequest ? RxUtil._main() : RxUtil._io_main())
                .compose(rxCache.transformer(cacheMode, proxy.getCallBack().getType()))
                .retryWhen(new RetryExceptionFunc(retryCount, retryDelay, retryIncreaseDelay));
    }

    @Override
    protected Observable<ResponseBody> generateRequest() {
        return null;
    }
}
