package com.soten.tablepay.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.soten.tablepay.R
import com.soten.tablepay.data.api.request.KakaoLoginRequest
import com.soten.tablepay.databinding.FragmentLoginBinding
import com.soten.tablepay.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        observeData()
    }

    private fun observeData() {
        loginViewModel.loginStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                true -> findNavController().navigate(R.id.navigationOwnerHome)
                false -> Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                null -> Toast.makeText(context, "네트워크 상태 불안정", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindView() {
        binding.kakaoLoginButton.setOnClickListener {
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("TablePay", "로그인 실패", error)
                } else if (token != null) {
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e("TablePay", "사용자 정보 요청 실패", error)
                        } else if (user != null) {
                            val loginRequest = KakaoLoginRequest(
                                provider = "KAKAO",
                                providerId = token.accessToken,
                                userType = if (binding.ownerButton.isChecked) "OWNER" else "CUSTOMER",
                                nickname = user.kakaoAccount?.profile?.nickname
                                    ?: "테이블페이 임시 닉네임"
                            )

                            loginViewModel.kakaoLogin(loginRequest)
                        }
                    }
                }
            }
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

}