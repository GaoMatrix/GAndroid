package com.gao.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gao.android.R;
import com.gao.android.db.DBManager;
import com.gao.android.model.Customer;
import com.gao.android.model.CustomerDao;
import com.gao.android.model.Order;
import com.gao.android.model.OrderDao;
import com.gao.android.model.User;
import com.gao.android.model.UserModel;
import com.orhanobut.logger.Logger;

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
    @BindView(R.id.toOne)
    Button toOne;
    @BindView(R.id.toMany)
    Button toMany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.insertUser, R.id.insertUserList,
            R.id.deleteUser, R.id.updateUser, R.id.queryUserList,
            R.id.queryUserListByAge, R.id.toOne, R.id.toMany})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insertUser:
                for (int i = 0; i < 5; i++) {
                    User user = new User();
                    //user.setId(i);
                    user.setAge(i * 3);
                    user.setName("第" + i + "人");
                    UserModel.insertUser(getApplicationContext(), user);
                }
                break;
            case R.id.insertUserList:
                List<User> userList = new ArrayList<User>();
                for (int i = 5; i < 10; i++) {
                    User user = new User();
                    //user.setId(i);
                    user.setAge(i * 3);
                    user.setName("第" + i + "人");
                    userList.add(user);
                }
                UserModel.insertUserList(getApplicationContext(), userList);
                break;
            case R.id.deleteUser:
                break;
            case R.id.updateUser:
                break;
            case R.id.queryUserList:
                List<User> users = UserModel.queryUserList(getApplicationContext());
                for (User user : users) {
                    Logger.d(user.toString());
                }
                break;
            case R.id.queryUserListByAge:
                break;
            case R.id.toOne:
                CustomerDao customerDao = DBManager.getInstance(getApplicationContext()).
                        getDaoSessionWritable().getCustomerDao();
                Customer customer = new Customer(1, "gao");
                customerDao.insert(customer);

                Order order = new Order();
                order.setId(1L);
                order.setCustomerId(customer.getId());
                OrderDao orderDao = DBManager.getInstance(getApplicationContext()).
                        getDaoSessionWritable().getOrderDao();
                orderDao.insert(order);

                break;
            case R.id.toMany:
                break;
        }
    }
}
