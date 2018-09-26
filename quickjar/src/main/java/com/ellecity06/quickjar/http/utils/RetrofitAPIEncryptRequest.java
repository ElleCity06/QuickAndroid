package com.ellecity06.quickjar.http.utils;


import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;


/**
 * 组装retrofit json报文, 具体由子类去实现,
 *
 * @author ellecity06
 * @time 2018/5/9 10:00
 * @des
 */

public abstract class RetrofitAPIEncryptRequest {


    private static final String TAG = RetrofitAPIEncryptRequest.class.getSimpleName();

    /**
     * @param map encrypt params 返回一个RequestBody对象
     */
    public RequestBody encrypt(Map<String, Object> map) {
        Gson gson = new Gson();
        String data = gson.toJson(map);
        HttpLog.d(data);
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
        return requestBody;
    }

    /**
     * @param map encrypt params 把一个map转为一个json字符串
     */
    public String encryptForString(Map<String, Object> map) {
        Gson gson = new Gson();
        String data = gson.toJson(map);
        HttpLog.d(data);
        return data;
    }

    /**
     * 构建加密参数
     *
     * @return 加密参数
     */
    public abstract RequestBody buildEncryptString();

}
