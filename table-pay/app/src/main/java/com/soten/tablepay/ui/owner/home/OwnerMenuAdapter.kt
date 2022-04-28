package com.soten.tablepay.ui.owner.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soten.tablepay.databinding.ItemOnwerMenuBinding
import com.soten.tablepay.domain.models.LottieModel

class OwnerMenuAdapter : RecyclerView.Adapter<OwnerMenuViewHolder>() {

    var items = emptyList<LottieModel>()

    fun setItem(list: List<LottieModel>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OwnerMenuViewHolder(
            ItemOnwerMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OwnerMenuViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}