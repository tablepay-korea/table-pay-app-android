package com.soten.tablepay.data.api.response

data class TablePayResponse<T>(
    val success: Boolean,
    val data: T
)