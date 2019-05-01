package com.example.collagehelper.dao;

import com.example.collagehelper.bean.APDO;
import com.example.collagehelper.bean.Assemble;
import com.example.collagehelper.bean.CTSDO;
import com.example.collagehelper.bean.GoodsAllInfo;
import com.example.collagehelper.bean.GoodsInfoFromServer;
import com.example.collagehelper.bean.Order;
import com.example.collagehelper.bean.ShoppingCart;
import com.example.collagehelper.bean.User;

import java.util.List;

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

    @GET("get_goods_by_name")
    Call<GoodsInfoFromServer> getGoodsByName(@Query("name")String name);

    @GET("collect_seller")
    Call<ResponseBody> collectSeller(@Query("customer_phone")String cPhone,@Query("seller_phone")String sPhone);

    @GET("get_seller")
    Call<CTSDO> getCollectedSeller(@Query("customer_phone")String cPhone);

    @GET("delete_seller")
    Call<ResponseBody> deleteSellerById(@Query("id")int id);

    @GET("add")
    Call<ResponseBody> addToCart(@Query("phone")String phone,@Query("goods_id")int goodsId,@Query("goods_count")int goodsCount);

    @GET("get")
    Call<ShoppingCart> getFromCart(@Query("phone")String phone);

    @GET("delete")
    Call<ResponseBody> delete(@Query("id") int id);

    @GET("add")
    Call<ResponseBody> addOrder(@Query("customer_phone")String customerPhone,@Query("seller_phone") String sellerPhone,@Query("order_id")String orderId,@Query("time")String time,@Query("money")String money,@Query("goods_id")int goodsId,@Query("goods_count")int goodsCount);

    @GET("sbcp")
    Call<List<Order>> selectByCustomerPhone(@Query("customer_phone") String customerPhone);

    @GET("sbsp")
    Call<List<Order>> selectBySellerPhone(@Query("seller_phone")String sellerPhone);

    @GET("add")
    Call<ResponseBody> addAssemble(@Query("customer_phone")String customerPhone,@Query("seller_phone") String sellerPhone,@Query("assemble_id")String assembleId,@Query("time")String time,@Query("money")String money,@Query("goods_id")int goodsId,@Query("goods_count")int goodsCount);

    @GET("get")
    Call<List<Assemble>> getAssemble();

    @GET("get")
    Call<List<APDO>> getAP(@Query("assemble_id")String assembleId);
}
