package com.example.collagehelper.activity.seller.bean;

/**
 * 商品的recyclerview 的 adpter的适配类
 * Created by liang on 2018/11/14
 */
public class GoodsAdapterBean {

    private int picture;
    private String name;
    private String price;

    public GoodsAdapterBean(int picture, String name, String price) {
        this.picture = picture;
        this.name = name;
        this.price = price;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
