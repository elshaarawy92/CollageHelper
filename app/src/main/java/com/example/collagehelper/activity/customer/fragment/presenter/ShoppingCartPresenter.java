package com.example.collagehelper.activity.customer.fragment.presenter;

import com.example.collagehelper.activity.customer.fragment.manager.ShoppingCartManager;
import com.example.collagehelper.activity.customer.fragment.view.IShoppingCartView;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.ShoppingCart;
import com.example.collagehelper.bean.ShoppingCartInfo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPresenter {
    private IShoppingCartView view;
    private ShoppingCartManager manager;

    public ShoppingCartPresenter(IShoppingCartView view){
        this.view = view;
        manager = new ShoppingCartManager(this);
    }

    public void getFromCartSuccess(ShoppingCart shoppingCart){
        List<ShoppingCartInfo> list = new ArrayList<>();
        for (int i = 0;i < shoppingCart.getData().size();i++){
            ShoppingCartInfo shoppingCartInfo = new ShoppingCartInfo();
            shoppingCartInfo.setId(shoppingCart.getData().get(i).getId());
            shoppingCartInfo.setGoodsCount(shoppingCart.getData().get(i).getGoodsCount());
            shoppingCartInfo.setPhone(shoppingCart.getData().get(i).getPhone());
            shoppingCartInfo.setGoodsId(shoppingCart.getData().get(i).getGoodsId());
            list.add(shoppingCartInfo);
        }
        view.getFromCartSuccess(list);
    }

    public void getFromCartNull(){
        view.getFromCartNull();
    }

    public void getFromCartFailure(){
        view.getFromCartFailure();
    }

    public void getFromCart(String phone){
        manager.getFromCart(phone);
    }

    public void getGoodsDetail(int id){
        manager.getGoodsDetail(id);
    }

    public void getGoodsSuccess(GoodsAllInfo goodsAllInfo){
        view.getGoodsSuccess(goodsAllInfo);
    }

    public void getGoodsFailure(){
        view.getGoodsFailure();
    }

    public void delete(int id){
        manager.delete(id);
    }

    public void deleteSuccess(){
        view.deleteSuccess();
    }

    public void deleteFailure(){
        view.deleteFailure();
    }
}
