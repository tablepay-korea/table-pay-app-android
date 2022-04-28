package com.soten.tablepay.data.api.request

data class KakaoLoginRequest(
    val provider: String, // KAKAO,
    val providerId: String, // accessToken
    val userType: String, // OWNER, CUSTOMER
    val nickname: String // 카카오 닉네임
)
