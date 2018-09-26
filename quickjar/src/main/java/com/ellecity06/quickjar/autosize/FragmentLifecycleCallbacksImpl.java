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
package com.ellecity06.quickjar.autosize;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * {@link FragmentLifecycleCallbacksImpl} 可用来代替在 BaseFragment 中加入适配代码的传统方式
 * {@link FragmentLifecycleCallbacksImpl} 这种方案类似于 AOP, 面向接口, 侵入性低, 方便统一管理, 扩展性强, 并且也支持适配三方库的 {@link Fragment}
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 16:43
 */
public class FragmentLifecycleCallbacksImpl extends FragmentManager.FragmentLifecycleCallbacks {
    /**
     * 屏幕适配逻辑策略类
     */
    private AutoAdaptStrategy mAutoAdaptStrategy;

    public FragmentLifecycleCallbacksImpl(AutoAdaptStrategy autoAdaptStrategy) {
        mAutoAdaptStrategy = autoAdaptStrategy;
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        if (mAutoAdaptStrategy != null) {
            mAutoAdaptStrategy.applyAdapt(f, f.getActivity());
        }
    }

    /**
     * 设置屏幕适配逻辑策略类
     *
     * @param autoAdaptStrategy {@link AutoAdaptStrategy}
     */
    public void setAutoAdaptStrategy(AutoAdaptStrategy autoAdaptStrategy) {
        mAutoAdaptStrategy = autoAdaptStrategy;
    }
}
