package com.soten.tablepay.data.api.response


import com.google.gson.annotations.SerializedName

data class ShopResisterResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("businessNumber")
    val businessNumber: String,
    @SerializedName("closeTime")
    val closeTime: String,
    @SerializedName("contactNumber")
    val contactNumber: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("detailAddress")
    val detailAddress: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("openTime")
    val openTime: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)