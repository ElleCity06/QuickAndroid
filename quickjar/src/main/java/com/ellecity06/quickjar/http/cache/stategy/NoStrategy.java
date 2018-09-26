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
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 网络加载，不缓存
 *
 * @author ellecity06
 * @time 2018/7/17 10:34
 * @des
 */
public class NoStrategy implements IStrategy {
    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String cacheKey, long cacheTime, Observable<T> source, Type type) {
        return source.map(new Function<T, CacheResult<T>>() {
            @Override
            public CacheResult<T> apply(@NonNull T t) throws Exception {
                return new CacheResult<T>(false, t);
            }
        });
    }
}
