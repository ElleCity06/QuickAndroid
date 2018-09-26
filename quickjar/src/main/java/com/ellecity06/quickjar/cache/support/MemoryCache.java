package com.ellecity06.quickjar.cache.support;

import android.text.TextUtils;
import android.util.LruCache;

import com.ellecity06.quickjar.http.utils.HttpLog;


/**
 * 内存缓存
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:21
 */
public class MemoryCache {
    private LruCache<String, Object> cache;

    public MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        cache = new LruCache(cacheSize);
    }

    public synchronized void put(String key, Object value) {
        if (TextUtils.isEmpty(key))
            return;

        if (cache.get(key) != null) {
            cache.remove(key);
        }
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public synchronized <T> T get(String key, Class<T> clazz) {
        try {
            return (T) cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            HttpLog.e(e);
        }
        return null;
    }

    public void remove(String key) {
        if (cache.get(key) != null) {
            cache.remove(key);
        }
    }

    public boolean contains(String key) {
        return cache.get(key) != null;
    }

    public void clear() {
        cache.evictAll();
    }
}
