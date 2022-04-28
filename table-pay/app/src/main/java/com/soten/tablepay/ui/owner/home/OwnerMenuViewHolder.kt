package com.soten.tablepay.ui.owner.home

import androidx.recyclerview.widget.RecyclerView
import com.soten.tablepay.databinding.ItemOnwerMenuBinding
import com.soten.tablepay.domain.models.LottieModel

class OwnerMenuViewHolder(
    val binding: ItemOnwerMenuBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lottieModel: LottieModel) {
        binding.lottieModel = lottieModel
        binding.lottieItem.setAnimationFromUrl(lottieModel.lottieUrl)
        binding.executePendingBindings()
    }

}