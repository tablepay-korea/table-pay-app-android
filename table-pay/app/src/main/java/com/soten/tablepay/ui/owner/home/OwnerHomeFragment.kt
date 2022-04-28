package com.soten.tablepay.ui.owner.home

import android.os.Bundle
import android.view.View
import com.soten.tablepay.R
import com.soten.tablepay.databinding.FragmentOwnerHomeBinding
import com.soten.tablepay.ui.base.BaseFragment
import com.soten.tablepay.utils.LottieAnimationFactory.lottieModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OwnerHomeFragment : BaseFragment<FragmentOwnerHomeBinding>(R.layout.fragment_owner_home) {

    private val adapter = OwnerMenuAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ownerMenuRecyclerView.adapter = adapter
        adapter.setItem(lottieModels)
    }

}