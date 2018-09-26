package com.ellecity06.quickjar.base.activity;


import com.ellecity06.quickjar.base.fragment.FragmentLifeCallback;

/**
 * 想要通过本库的{@link ActivityLifeCallback 实现相关基类功能，那么你的Acticity必须实现此接口,通常需要在BaseActivity中实现}
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 16:49
 */

public interface IBaseActivity {

    /**
     * 该Activity是否订阅事件总线
     * @return true则自动进行注册/注销操作，false则不注册
     */
    boolean isUseEventBus();

    /**
     * 该Activity是否包含Fragment（是否注册FragmentLifecycleCallbacks）
     * @return
     * 返回false则不注册FragmentLifecycleCallbacks，也就是说{@link FragmentLifeCallback}中的操作不会进行
     */
    boolean isUseFragment();
}
