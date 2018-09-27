package com.zxy.mytsfqxproject.http

import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.mvp.entity.UserBean
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("Login/sendSmsCode")
    fun yanSmsCode(@Query("phone") phone: String, @Query("type") type: String, @Query("verify_code") verify_code: String): Call<JsonObject>

    @POST("login/findLoginPwd")
    fun findLoginPwd(@Query("phone") phone: String, @Query("sms_code") sms_code: String, @Query("pwd") pwd: String, @Query("confirm_pwd") confirm_pwd: String): Call<JsonObject>

    @POST("Login/login")
    fun login(@Query("phone") phone: String, @Query("pwd") pwd: String): Call<UserBean>


}