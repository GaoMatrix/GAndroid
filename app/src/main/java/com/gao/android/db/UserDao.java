package com.gao.android.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
public class UserDao {
    private Dao<User, Integer> mUserDaoOpe;
    private DatabaseHelper mDatabaseHelper;

    public UserDao(Context context) {
        mDatabaseHelper = DatabaseHelper.getHelper(context);
        try {
            mUserDaoOpe = mDatabaseHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(User user) {
        try {
            mUserDaoOpe.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(int i) {
        try {
            return mUserDaoOpe.queryForId(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
