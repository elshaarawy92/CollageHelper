package com.example.collagehelper.activity.seller.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 订单表
 * Created by liang on 2018/11/13
 */
@Entity(tableName = "order")
public class Order {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "customer_id")
    public int customerId;

    @ColumnInfo(name = "sum_of_money")
    public double sumOfMoney;

    @ColumnInfo(name = "item_number")
    public int itemNumber;

    @ColumnInfo(name = "address_id")
    public int addressId;

    public String time;

    public int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(double sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
