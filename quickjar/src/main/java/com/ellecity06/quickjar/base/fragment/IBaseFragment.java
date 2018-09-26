package com.ellecity06.quickjar.base.fragment;

import android.os.Bundle;

/**
 * 想要通过{@link FragmentLifeCallback}实现相关的基类功能，那么你的Fragment 需实现此接口
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:00
 */

public interface IBaseFragment {

    /**
     * 需要保存数据时，将数据写进bundleToSave
     */
    void onSaveState(Bundle bundleToSave);

    /**
     * 从bundleToRestore中获取你保存金曲的数据
     */
    void onRestoreState(Bundle bundleToRestore);

    /**
     * 该Fragment是否订阅事件总线
     *
     * @return true则自动进行注册/注销操作，false则不注册
     */
    boolean isUseEventBus();

}
