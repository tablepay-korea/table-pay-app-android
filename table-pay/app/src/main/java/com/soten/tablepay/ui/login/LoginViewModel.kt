package com.soten.tablepay.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soten.tablepay.data.api.TablePayService
import com.soten.tablepay.data.api.request.KakaoLoginRequest
import com.soten.tablepay.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val tablePayService: TablePayService
) : BaseViewModel() {

    val loginStateLiveData = MutableLiveData<Boolean?>()

    fun kakaoLogin(kakaoLoginRequest: KakaoLoginRequest) = viewModelScope.launch {
        val response = safeApiCall { tablePayService.login(kakaoLoginRequest) }?.body()

        if (response == null) {
            loginStateLiveData.value = null
            return@launch
        }

        loginStateLiveData.value = response.success
    }

}