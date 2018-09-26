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

package com.ellecity06.quickjar.http.cache.converter;


import com.ellecity06.quickjar.http.utils.HttpLog;
import com.ellecity06.quickjar.http.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ConcurrentModificationException;

/**
 * GSON数据转换器<br>
 * 1.GSON-数据转换器其实就是存储字符串的操作<br>
 * 2.如果你的Gson有特殊处理，可以自己创建一个，否则用默认。<br>
 * 优点：<br>
 * 相对于SerializableDiskConverter转换器，存储的对象不需要进行序列化（Serializable），
 * 特别是一个对象中又包含很多其它对象，每个对象都需要Serializable，比较麻烦
 * 缺点：
 * 就是存储和读取都要使用Gson进行转换，object->String->Object的给一个过程，相对来说
 * 每次都要转换性能略低，但是以现在的手机性能可以忽略不计了。
 *
 * @author ellecity06
 * @time 2018/7/17 9:59
 * @des
 */
@SuppressWarnings(value = {"unchecked", "deprecation"})
public class GsonDiskConverter implements IDiskConverter {
    private Gson gson = new Gson();

    public GsonDiskConverter() {
        this.gson = new Gson();
    }

    public GsonDiskConverter(Gson gson) {
        Utils.checkNotNull(gson, "gson ==null");
        this.gson = gson;
    }

    @Override
    public <T> T load(InputStream source, Type type) {
        T value = null;
        try {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            JsonReader jsonReader = gson.newJsonReader(new InputStreamReader(source));
            value = (T) adapter.read(jsonReader);
            //value = gson.fromJson(new InputStreamReader(source), type);
        } catch (JsonIOException | IOException | ConcurrentModificationException | JsonSyntaxException e) {
            HttpLog.e(e.getMessage());
        } catch (Exception e) {
            HttpLog.e(e.getMessage());
        } finally {
            Utils.close(source);
        }
        return value;
    }

    @Override
    public boolean writer(OutputStream sink, Object data) {
        try {
            String json = gson.toJson(data);
            byte[] bytes = json.getBytes();
            sink.write(bytes, 0, bytes.length);
            sink.flush();
            return true;
        } catch (JsonIOException | JsonSyntaxException | ConcurrentModificationException | IOException e) {
            HttpLog.e(e.getMessage());
        } catch (Exception e) {
            HttpLog.e(e.getMessage());
        } finally {
            Utils.close(sink);
        }
        return false;
    }

}
