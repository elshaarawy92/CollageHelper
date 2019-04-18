package com.example.collagehelper.activity.customer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collagehelper.R;
import com.example.collagehelper.base.BaseFragment;

public class ShoppingCartFragment extends BaseFragment {
    private RecyclerView rvShoppingCart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart,container,false);
        rvShoppingCart = view.findViewById(R.id.rv_shopping_cart);
        return view;
    }
}
