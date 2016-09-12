package com.gao.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gao.android.model.DaoMaster;
import com.gao.android.model.DaoSession;

/**
 * Created by GaoMatrix on 2016/9/8.
 */
public class DBManager {
    private static final String DB_NAME = "gao";
    private static DBManager mInstance;
    private Context mContext;
    private DaoMaster.DevOpenHelper mOpenHelper;

    private DBManager(Context context) {
        mContext = context;
        mOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
    }

    public static synchronized DBManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBManager(context);
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     *
     * @return SQLiteDatabase
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mOpenHelper == null) {
            mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return SQLiteDatabase
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mOpenHelper == null) {
            mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db;
    }

    /**
     * 获得Writable的DaoSession
     * @return
     */
    public DaoSession getDaoSessionWritable() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }

    /**
     * 获得Readable的DaoSession
     * @return
     */
    public DaoSession getDaoSessionReadable() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }

}
