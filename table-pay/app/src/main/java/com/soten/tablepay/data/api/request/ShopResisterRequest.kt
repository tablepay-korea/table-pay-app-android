package com.soten.tablepay.data.api.request


import com.google.gson.annotations.SerializedName

data class ShopResisterRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("businessNumber")
    val businessNumber: String,
    @SerializedName("closeTime")
    val closeTime: String,
    @SerializedName("contactNumber")
    val contactNumber: String,
    @SerializedName("detailAddress")
    val detailAddress: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("openTime")
    val openTime: String,
    @SerializedName("title")
    val title: String
)