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

package com.ellecity06.quickjar.http.cache.core;


import com.ellecity06.quickjar.http.utils.HttpLog;
import com.ellecity06.quickjar.http.utils.Utils;

import java.lang.reflect.Type;

import okio.ByteString;


/**
 * 缓存的核心管理类<br>
 * 1，采用lruDiskCache<br>
 * 2，对key进行MD5加密
 * ！！ByteString其实已经很强大了，不需要我们自己再去处理加密了
 *
 * @author ellecity06
 * @time 2018/7/17 10:15
 * @des
 */

public class CacheCore {

    private LruDiskCache disk;

    public CacheCore(LruDiskCache disk) {
        this.disk = Utils.checkNotNull(disk, "disk==null");
    }


    /**
     * 读取
     */
    public synchronized <T> T load(Type type, String key, long time) {
        String cacheKey = ByteString.of(key.getBytes()).md5().hex();
        HttpLog.d("loadCache  key=" + cacheKey);
        if (disk != null) {
            T result = disk.load(type, cacheKey, time);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * 保存
     */
    public synchronized <T> boolean save(String key, T value) {
        String cacheKey = ByteString.of(key.getBytes()).md5().hex();
        HttpLog.d("saveCache  key=" + cacheKey);
        return disk.save(cacheKey, value);
    }

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    public synchronized boolean containsKey(String key) {
        String cacheKey = ByteString.of(key.getBytes()).md5().hex();
        HttpLog.d("containsCache  key=" + cacheKey);
        if (disk != null) {
            if (disk.containsKey(cacheKey)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public synchronized boolean remove(String key) {
        String cacheKey = ByteString.of(key.getBytes()).md5().hex();
        HttpLog.d("removeCache  key=" + cacheKey);
        if (disk != null) {
            return disk.remove(cacheKey);
        }
        return true;
    }

    /**
     * 清空缓存
     */
    public synchronized boolean clear() {
        if (disk != null) {
            return disk.clear();
        }
        return false;
    }

}
