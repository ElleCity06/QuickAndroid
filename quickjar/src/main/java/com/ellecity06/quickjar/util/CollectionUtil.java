package com.ellecity06.quickjar.util;

import android.text.TextUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合判断工具
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 14:14
 */
public class CollectionUtil {

    /**
     * 判断是否为空
     *
     * @param collection 集合
     * @return 空位true, 否则为false
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断是否为空
     *
     * @param map map
     * @return 空位true, 否则为false
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断是否包含字符串
     *
     * @param arrs 数组
     * @param val  是否包含的字符串
     * @return 包含返回true, 否则为false
     */
    public static boolean isContains(String[] arrs, String val) {
        if (arrs == null || TextUtils.isEmpty(val)) {
            return false;
        }
        for (String arrVal : arrs) {
            if (val.equals(arrVal)) {
                return true;
            }
        }
        return false;
    }
}
