package com.gao.android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
@Entity
public class Customer {
    @Id
    private long id;

    private String name;

    @Generated(hash = 1039711609)
    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
