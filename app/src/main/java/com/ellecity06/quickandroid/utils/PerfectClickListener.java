package com.ellecity06.quickandroid.utils;

import android.view.View;
import android.view.View.OnClickListener;

import java.util.Calendar;

/**
 * 避免一秒内多次点击
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/29 14:33
 */
public abstract class PerfectClickListener implements OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int id = -1;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId = v.getId();
        if (id != mId) {
            id = mId;
            lastClickTime = currentTime;
            onNoDoubleClick(v);
            return;
        }
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);
}
