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

package com.ellecity06.quickjar.http.cache.stategy;


import com.ellecity06.quickjar.http.cache.RxCache;
import com.ellecity06.quickjar.http.cache.model.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * 只加载网络 ，不请求缓存
 *
 * @author ellecity06
 * @time 2018/7/17 10:40
 * @des
 */
public final class OnlyRemoteStrategy extends BaseStrategy {
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key, long time, Observable<T> source, Type type) {
        return loadRemote(rxCache, key, source, false);
    }
}
