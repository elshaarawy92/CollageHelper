package com.example.collagehelper.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * 用户表
 * Created by liang on 2018/11/13
 */
@Entity(tableName = "user",indices = {@Index(value = "phone_number",unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    public String name;

    @ColumnInfo(name = "s_head")
    public String sHead;

    public String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsHead() {
        return sHead;
    }

    public void setsHead(String sHead) {
        this.sHead = sHead;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
