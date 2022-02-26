package com.soten.tablepay.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.soten.tablepay.BuildConfig
import com.soten.tablepay.TablePayService
import com.soten.tablepay.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.TABLE_PAY_BASE_URL)
        .build()
        .create(TablePayService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.textHome.setOnClickListener {
            retrofit.test2("hello").enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "성공안됨", Toast.LENGTH_SHORT).show()
                        Log.d("TablePay", "${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show()
                    Log.d("TablePay", "${t}")
                }
            })


           /* val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("Sopist", "로그인 실패", error)
                } else if (token != null) {
                    token.accessToken
                    token.refreshToken
                    token.accessTokenExpiresAt.toString()

                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e("TablePay", "사용자 정보 요청 실패", error)
                        } else if (user != null) {
                            Log.i(
                                "TablePay", "사용자 정보 요청 성공" +
                                        "\n회원번호: ${user.id}" +
                                        "\n이메일: ${user.kakaoAccount?.email}" +
                                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                            )
                        }
                    }
                }
            }
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)*/

        }
        // http://3.38.62.132/auth/kakao/owner
        // http://3.38.62.132/auth/kakao/customer
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}