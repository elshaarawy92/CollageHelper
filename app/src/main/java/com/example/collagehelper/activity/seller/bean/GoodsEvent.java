package com.example.collagehelper.activity.seller.bean;

import java.io.Serializable;

/**
 * 订单信息的辅助存储类
 * Created by liang on 2018/11/15
 */
public class GoodsEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    public String name;
    public int account;
    public double price;
    public double accountPrice;

    public GoodsEvent(String name, int account, double price, double accountPrice) {
        this.name = name;
        this.account = account;
        this.price = price;
        this.accountPrice = accountPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAccountPrice() {
        return accountPrice;
    }

    public void setAccountPrice(double accountPrice) {
        this.accountPrice = accountPrice;
    }
}
