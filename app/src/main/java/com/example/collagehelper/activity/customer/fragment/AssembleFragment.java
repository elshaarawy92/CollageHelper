package com.example.collagehelper.activity.customer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collagehelper.R;
import com.example.collagehelper.base.BaseFragment;

public class AssembleFragment extends BaseFragment {
    private SearchView svAssemble;
    private RecyclerView rvAssemble;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assemble,container,false);
        svAssemble = view.findViewById(R.id.sv_search_assemble);
        rvAssemble = view.findViewById(R.id.rv_assemble);
        return view;
    }
}
