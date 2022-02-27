package com.soten.tablepay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TablePayService {

    @GET("auth/kakao/owner")
    fun test(
        @Header("TablePay") header: String
    ): Call<Unit>

    @GET("/")
    fun test2(
        @Header("Authorization") header: String
    ): Call<Unit>

//    @GET("auth/login")
//    fun login(): Call<>

}