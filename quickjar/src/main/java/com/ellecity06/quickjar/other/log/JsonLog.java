package com.ellecity06.quickjar.other.log;


import android.util.Log;

import com.ellecity06.quickjar.other.ElleCityLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 打印json数据,长度无限制
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:39
 */
public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(ElleCityLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(ElleCityLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        PrintUtil.printLine(tag, true);
        message = headString + ElleCityLog.LINE_SEPARATOR + message;
        String[] lines = message.split(ElleCityLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        PrintUtil.printLine(tag, false);
    }
}
