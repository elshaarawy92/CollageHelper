package com.example.collagehelper.activity.customer.assemble.view;

import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public interface IAssembleView {
    void getAssembleSuccess(List<APDO> list);
    void getAssembleFailure();
    void getByAssembleIdSuccess(List<Assemble> list);
    void getByAssembleIdFailure();
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
    void getAPSuccess(List<APDO> list);
    void getAPFailure();
    void updateApSuccess();
    void updateApFailure();
    void addOrderSuccess();
    void addOrderFailure();
}
