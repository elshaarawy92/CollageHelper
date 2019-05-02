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

    private Retrofit retrofit4 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/cart/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask cartAsk = retrofit4.create(Ask.class);

    private Retrofit retrofit5 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/order/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask orderAsk = retrofit5.create(Ask.class);

    private Retrofit retrofit6 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/assemble/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask assembleAsk = retrofit6.create(Ask.class);

    private Retrofit retrofit7 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/ap/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask apAsk = retrofit7.create(Ask.class);

    private Retrofit retrofit8 = new Retrofit.Builder()
            .baseUrl("http://192.168.43.88:8080/cg/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public Ask cgAsk = retrofit8.create(Ask.class);

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
}
