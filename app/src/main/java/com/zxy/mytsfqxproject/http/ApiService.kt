package com.zxy.mytsfqxproject.http

import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.mvp.entity.CarDetailBean
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer
import com.zxy.mytsfqxproject.mvp.entity.MenuBean
import com.zxy.mytsfqxproject.mvp.entity.UserBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("Login/sendSmsCode")
    fun yanSmsCode(@Body route: RequestBody): Call<JsonObject>

    @POST("login/findLoginPwd")
    fun findLoginPwd(@Body route: RequestBody): Call<JsonObject>

    @POST("Login/login")
    fun login(@Body route: RequestBody): Call<UserBean>

    @POST("Appmenu/getMenu")
    fun getMenu(@Body route: RequestBody): Call<MenuBean>

    //获取当前车牌的所有信息
    @POST("Communal/getClientDetail")
    fun getClientDetail(@Body route: RequestBody): Observable<CarDetailBean>

    //获取客户列表
    @POST("Client/applists")
    fun getChooseCustomer(@Body route: RequestBody): Call<ChooseCustomer>

    @POST("Client/addClient")
    fun addClient(@Body route: RequestBody): Call<JsonObject>

    @POST("Client/editClient")
    fun editClient(@Body route: RequestBody): Call<JsonObject>

    @POST("Client/applists")//客户管理
    fun applists(@Body route: RequestBody): Call<ChooseCustomer>
}