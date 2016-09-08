package com.gao.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gao.android.R;
import com.gao.android.db.DBManager;
import com.gao.android.model.DaoMaster;
import com.gao.android.model.DaoSession;
import com.gao.android.model.User;
import com.gao.android.model.UserDao;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/9/8.
 */
public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.insertUser)
    Button insertUser;
    @BindView(R.id.insertUserList)
    Button insertUserList;
    @BindView(R.id.deleteUser)
    Button deleteUser;
    @BindView(R.id.updateUser)
    Button updateUser;
    @BindView(R.id.queryUserList)
    Button queryUserList;
    @BindView(R.id.queryUserListByAge)
    Button queryUserListByAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.insertUser, R.id.insertUserList, R.id.deleteUser, R.id.updateUser, R.id.queryUserList, R.id.queryUserListByAge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insertUser:
                for (int i = 0; i < 5; i++) {
                    User user = new User();
                    user.setId(i);
                    user.setAge(i * 3);
                    user.setName("第" + i + "人");
                    insertUser(user);
                }
                break;
            case R.id.insertUserList:
                List<User> userList = new ArrayList<User>();
                for (int i = 5; i < 10; i++) {
                    User user = new User();
                    user.setId(i);
                    user.setAge(i * 3);
                    user.setName("第" + i + "人");
                    userList.add(user);
                }
                insertUserList(userList);
                break;
            case R.id.deleteUser:
                break;
            case R.id.updateUser:
                break;
            case R.id.queryUserList:
                List<User> users = queryUserList();
                for (User user : users) {
                    Logger.init().hideThreadInfo().methodCount(0);
                    Logger.d(user.toString());
                }
                break;
            case R.id.queryUserListByAge:
                break;
        }
    }

    private void insertUserList(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        UserDao userDao = getWritableUserDao();
        userDao.insertInTx(userList);
    }

    private UserDao getWritableUserDao() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(this).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao;
    }

    private UserDao getReadableUserDao() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(this).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao;
    }

    private void insertUser(User user) {
        UserDao userDao = getWritableUserDao();
        if (userDao != null) {
            userDao.insert(user);
        }
    }

    private List<User> queryUserList() {
        UserDao userDao = getReadableUserDao();
        QueryBuilder<User> queryBuilder = userDao.queryBuilder();
        List<User> list = queryBuilder.list();
        return list;
    }


}
