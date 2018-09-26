package com.ellecity06.quickjar.other.log;


import android.util.Log;

import com.ellecity06.quickjar.other.ElleCityLog;


/**
 * log的基类
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:38
 */
public class BaseLog {

    public static void printDefault(int type, String tag, String msg) {

        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(type, tag, sub);
                index += maxLength;
            }
            printSub(type, tag, msg.substring(index, msg.length()));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case ElleCityLog.V:
                Log.v(tag, sub);
                break;
            case ElleCityLog.D:
                Log.d(tag, sub);
                break;
            case ElleCityLog.I:
                Log.i(tag, sub);
                break;
            case ElleCityLog.W:
                Log.w(tag, sub);
                break;
            case ElleCityLog.E:
                Log.e(tag, sub);
                break;
            case ElleCityLog.A:
                Log.wtf(tag, sub);
                break;
        }
    }

}
