package com.ellecity06.quickjar.other.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * 打印工具，为了美观，格式易于观看
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:39
 */
public class PrintUtil {

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}
