package com.soten.tablepay.ui.customer.settings

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.soten.tablepay.R
import com.soten.tablepay.data.api.TablePayService
import com.soten.tablepay.data.api.request.KakaoLoginRequest
import com.soten.tablepay.databinding.FragmentCustomerSettingsBinding
import com.soten.tablepay.ui.base.BaseFragment
import com.soten.tablepay.utils.ThemeUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentCustomerSettingsBinding>(
    R.layout.fragment_customer_settings
) {

    private val settingsViewModel by viewModels<SettingsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}