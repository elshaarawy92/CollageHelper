package com.example.collagehelper.bean;

public class SCOrder {
    private String customerPhone;
    private String sellerPhone;
    private String orderId;
    private String time;
    private String money;
    private int goodsId;
    private int goodsCount;
    private String status;
    private int scId;

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    @Override
    public String toString() {
        return "SCOrder{" +
                "customerPhone='" + customerPhone + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", orderId='" + orderId + '\'' +
                ", time='" + time + '\'' +
                ", money='" + money + '\'' +
                ", goodsId=" + goodsId +
                ", goodsCount=" + goodsCount +
                ", status='" + status + '\'' +
                ", scId=" + scId +
                '}';
    }
}
