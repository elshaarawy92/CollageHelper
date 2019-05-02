package com.example.collagehelper.activity.customer.mycollect.view;

import com.example.collagehelper.bean.CGDO;
import com.example.collagehelper.bean.GoodsAllInfo;

import java.util.List;

public interface IMyCollectView {
    void getCgSuccess(List<CGDO> list);
    void getCgFailure();
    void deleteCgSuccess();
    void deleteCgFailure();
    void getGoodsSuccess(GoodsAllInfo goodsAllInfo);
    void getGoodsFailure();
}
