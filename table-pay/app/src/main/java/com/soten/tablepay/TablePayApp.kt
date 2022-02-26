package com.soten.tablepay

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class TablePayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.native_key))
    }

}