package com.example.collagehelper.bean;

public class ChatDO {
    private int id;
    private String customerPhone;
    private String sellerPhone;
    private String message;
    private String time;
    private String start;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "ChatDO{" +
                "id=" + id +
                ", customerPhone='" + customerPhone + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", start='" + start + '\'' +
                '}';
    }
}
