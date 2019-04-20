package com.example.collagehelper.base;

import com.example.collagehelper.dao.Ask;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseManager {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask ask = retrofit.create(Ask.class);

    private Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/goods/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public Ask goodsAsk = retrofit2.create(Ask.class);

    private Retrofit retrofit3 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/cts/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask ctsAsk = retrofit3.create(Ask.class);

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
}
