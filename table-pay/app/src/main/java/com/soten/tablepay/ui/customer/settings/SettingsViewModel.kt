package com.soten.tablepay.ui.customer.settings

import androidx.lifecycle.ViewModel
import com.soten.tablepay.data.api.TablePayService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val tablePayService: TablePayService
): ViewModel() {



}