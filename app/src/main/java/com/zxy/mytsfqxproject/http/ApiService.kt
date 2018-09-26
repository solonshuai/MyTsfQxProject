package com.zxy.mytsfqxproject.http

import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("Login/sendSmsCode")
    fun yanSmsCode(@Query("phone") phone: String, @Query("type") type: String, @Query("verify_code") verify_code: String): Observable<String>
}