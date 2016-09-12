package com.gao.android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
@Entity
public class Order {
    @Id
    private Long id;

    private Long customerId;

    @ToOne(joinProperty = "customerId")
    private Customer customer;

    public Order(Long id, Long customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
