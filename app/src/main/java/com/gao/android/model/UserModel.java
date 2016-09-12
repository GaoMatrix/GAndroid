package com.gao.android.model;

import android.content.Context;

import com.gao.android.db.DBManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
public class UserModel {
    public static void insertUserList(Context context, List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        UserDao userDao = getWritableUserDao(context);
        userDao.insertInTx(userList);
    }

    public static void insertUser(Context context, User user) {
        UserDao userDao = getWritableUserDao(context);
        if (userDao != null) {
            userDao.insert(user);
        }
    }

    public static List<User> queryUserList(Context context) {
        UserDao userDao = getReadableUserDao(context);
        QueryBuilder<User> queryBuilder = userDao.queryBuilder();
        List<User> list = queryBuilder.list();
        return list;
    }

    private static UserDao getReadableUserDao(Context context) {
        return DBManager.getInstance(context).getDaoSessionWritable().getUserDao();
    }

    private static UserDao getWritableUserDao(Context context) {
        return DBManager.getInstance(context).getDaoSessionReadable().getUserDao();
    }
}
