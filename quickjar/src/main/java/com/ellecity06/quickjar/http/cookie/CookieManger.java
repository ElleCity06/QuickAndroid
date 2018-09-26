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

package com.ellecity06.quickjar.http.cookie;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;


/**
 * cookie 管理器
 *
 * @author ellecity06
 * @time 2018/7/17 11:09
 * @des
 */
public class CookieManger implements CookieJar {

    private static Context mContext;
    private static PersistentCookieStore cookieStore;

    public CookieManger(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }

    public void addCookies(List<Cookie> cookies) {
        cookieStore.addCookies(cookies);
    }

    public void saveFromResponse(HttpUrl url, Cookie cookie) {
        if (cookie != null) {
            cookieStore.add(url, cookie);
        }
    }

    public PersistentCookieStore getCookieStore() {
        return cookieStore;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }


    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public void remove(HttpUrl url, Cookie cookie) {
        cookieStore.remove(url, cookie);
    }

    public void removeAll() {
        cookieStore.removeAll();
    }

}
