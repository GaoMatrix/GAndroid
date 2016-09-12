package com.gao.android.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
@Entity(indexes = {
        @Index(value = "age,date DESC", unique = true),
        }// 字段age，date加索引，并表明唯一
        ,
        nameInDb = "DB_USER"// 自定义表名
)
public class User {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "USERNAME")
    @NotNull
    @Unique
    private String name;

    @NotNull
    private int age;

    private Date date;

    @Generated(hash = 687744679)
    public User(Long id, @NotNull String name, int age, Date date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date = date;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
