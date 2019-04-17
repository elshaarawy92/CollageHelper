package com.example.collagehelper.dao;

import com.example.collagehelper.activity.seller.bean.GoodsAllInfo;
import com.example.collagehelper.activity.seller.bean.GoodsInfoFromServer;
import com.example.collagehelper.activity.seller.bean.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Ask {
    @POST("regist")
    @Multipart
    Call<Integer> regist(@Part("phone") RequestBody phone, @Part("name") RequestBody name, @Part("pwd") RequestBody pwd, @Part("type") RequestBody type,@Part() MultipartBody.Part file);

    @GET("get")
    Call<User> getUser(@Query("phone")String phone);

    @POST("add_goods")
    @Multipart
    Call<ResponseBody> addGoods(@Part("phone")RequestBody phone, @Part("goods_name")RequestBody goodsName, @Part("goods_price")RequestBody goodsPrice, @Part("goods_des")RequestBody goodsDes, @Part()MultipartBody.Part file);

    @GET("get_goods")
    Call<GoodsInfoFromServer> getGoods(@Query("phone")String phone);

    @POST("delete_goods")
    @FormUrlEncoded
    Call<ResponseBody> deleteGoods(@Field("id")int id);

    @POST("update_goods")
    @Multipart
    Call<ResponseBody> updateGoods(@Part("id")RequestBody id,@Part("phone")RequestBody phone, @Part("goods_name")RequestBody goodsName, @Part("goods_price")RequestBody goodsPrice, @Part("goods_des")RequestBody goodsDes, @Part()MultipartBody.Part file);

    @GET("get_goods_by_id")
    Call<GoodsAllInfo> getGoodsById(@Query("id")int id);
}
