package com.soten.tablepay.data.api.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val id: String,
    val providerId: String
)
