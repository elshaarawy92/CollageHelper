package com.example.collagehelper.bean;

/**
 * 我的界面Recyclerview的数据适配bean类(同样适用于Customer的recyclerview)
 * Created by liang on 2018/10/29
 */
public class Function {

    private int picture;

    private String text;

    public Function(){

    }

    public Function(int picture, String text) {
        this.picture = picture;
        this.text = text;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
