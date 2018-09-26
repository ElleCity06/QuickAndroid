/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ellecity06.quickjar.autosize.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * AndroidAutoSize 常用工具类
 * 目前提供两个方法 dp2px 和px2dp
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 16:33
 */
public class AutoSizeUtils {

    private AutoSizeUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static int dp2px(Context context, float value) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    public static int sp2px(Context context, float value) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics()) + 0.5f);
    }
}
