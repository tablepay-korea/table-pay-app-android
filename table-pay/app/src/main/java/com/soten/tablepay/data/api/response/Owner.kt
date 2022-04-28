package com.soten.tablepay.data.api.response


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providerId")
    val providerId: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)