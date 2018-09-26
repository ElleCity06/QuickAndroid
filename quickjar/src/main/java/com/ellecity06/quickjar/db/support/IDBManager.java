package com.ellecity06.quickjar.db.support;

import android.support.v4.util.SimpleArrayMap;

/**
 * 数据库接口管理者
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:29
 */

public interface IDBManager {

    void init();//进行初始化操作，如建库建表

    //将各个表的管理者存进mapTables中
    //请记清楚key值，后面对数据表的操作是通过DevRing.tableManager(key)方法得到对应的数据表管理者，然后进行增删改查操作。
    void putTableManager(SimpleArrayMap<Object, ITableManger> mapTables);

}
