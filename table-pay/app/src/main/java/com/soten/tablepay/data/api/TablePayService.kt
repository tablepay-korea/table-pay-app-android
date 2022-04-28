package com.soten.tablepay.data.api

import com.soten.tablepay.data.api.request.KakaoLoginRequest
import com.soten.tablepay.data.api.request.ShopResisterRequest
import com.soten.tablepay.data.api.response.LoginResponse
import com.soten.tablepay.data.api.response.ShopResisterResponse
import com.soten.tablepay.data.api.response.TablePayResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TablePayService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: KakaoLoginRequest): Response<TablePayResponse<LoginResponse>>

    @POST("shop")
    suspend fun resisterShop(
        @Header("Authorization") token: String,
        @Body shopResisterRequest: ShopResisterRequest
    ): Response<ShopResisterResponse>

}