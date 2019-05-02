package com.example.collagehelper.activity.customer.fragment.view;

import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public interface IAssembleView {
    void getAssembleSuccess(List<Assemble> list);
    void getAssembleFailure();
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
    void getAPSuccess(List<APDO> list);
    void getAPFailure();
    void joinSuccess();
    void alreadyJoin();
    void joinFailure();
}
