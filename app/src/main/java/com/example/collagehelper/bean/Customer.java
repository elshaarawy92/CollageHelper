package com.example.collagehelper.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * 客户表
 * Created by liang on2018/11/13
 */
@Entity(tableName = "customer",indices = {@Index(value = "phone_number",unique = true)})
public class Customer {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    public String name;

    @ColumnInfo(name = "replace_name")
    public String replaceName;

    @ColumnInfo(name = "address_id")
    public int addressId;

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

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
