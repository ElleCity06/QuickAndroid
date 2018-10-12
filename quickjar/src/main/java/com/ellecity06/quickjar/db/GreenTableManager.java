package com.ellecity06.quickjar.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;

import com.ellecity06.quickjar.db.support.ITableManger;
import com.ellecity06.quickjar.http.utils.HttpLog;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * author:  ljy
 * date:    2018/3/10
 * description: GreenDao基本的表管理者
 * ITableManger以外的方法，请通过QuickAndroid.<GreenTableManager>tableManager(key)来调用
 * <p>
 * <a>https://www.jianshu.com/p/11bdd9d761e6</a>
 */

/**
 * GreenDao基本的表管理者 ,
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:31
 */

public abstract class GreenTableManager<M, K> implements ITableManger<M, K> {

    //重写此方法，并将GreenDao自动生成的XXXDao返回。
    public abstract AbstractDao<M, K> getDao();

    @Override
    public boolean insertOne(@NotNull M m) {
        try {
            getDao().insert(m);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplaceOne(@NotNull M m) {
        try {
            getDao().insertOrReplace(m);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean insertSome(@NotNull List<M> list) {
        try {
            getDao().insertInTx(list);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplaceSome(@NotNull List<M> list) {
        try {
            getDao().insertOrReplaceInTx(list);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOne(@NotNull M m) {
        try {
            getDao().delete(m);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOneByKey(@NotNull K key) {
        try {
            getDao().deleteByKey(key);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSome(@NotNull List<M> list) {
        try {
            getDao().deleteInTx(list);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public final boolean deleteSomeByKeys(@NotNull List<K> list) {
        try {
            getDao().deleteByKeyInTx(list);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try {
            getDao().deleteAll();
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateOne(@NotNull M m) {
        try {
            getDao().update(m);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSome(@NotNull List<M> list) {
        try {
            getDao().updateInTx(list);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    @Override
    public M loadOne(@NotNull K key) {
        try {
            return getDao().load(key);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return null;
        }
    }

    @Override
    public List<M> loadAll() {
        return getDao().loadAll();
    }

    @Override
    public long count() {
        return getDao().count();
    }

    @Override
    public Cursor rawQuery(String sql, String[] selectionArgs) {
        try {
            return getDao().getSession().getDatabase().rawQuery(sql, selectionArgs);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return null;
        }
    }

    @Override
    public boolean execSQL(String sql) {
        try {
            getDao().getSession().getDatabase().execSQL(sql);
        } catch (SQLException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    //下面为ITableManager接口以外的方法

    public boolean refresh(@NotNull M m) {
        try {
            getDao().refresh(m);
        } catch (SQLiteException e) {
            HttpLog.e(e);
            return false;
        }
        return true;
    }

    public void runInTx(@NotNull Runnable runnable) {
        try {
            getDao().getSession().runInTx(runnable);
        } catch (SQLiteException e) {
            HttpLog.e(e);
        }
    }

    public QueryBuilder<M> queryBuilder() {
        return getDao().queryBuilder();
    }

    public Query<M> queryRawCreate(@NotNull String where, @NotNull Object... selectionArg) {
        return getDao().queryRawCreate(where, selectionArg);
    }

    public void clearCache() {
        getDao().detachAll();
    }

}
