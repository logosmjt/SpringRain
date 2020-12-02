package com.jingtian.springrain.ui.home

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.databinding.ListItemBannerBinding

class BannerViewHolder(private val binding: ListItemBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.setClickListener {
            binding.banner?.let { banner ->
                Log.i("OperationAdapter", "banner url :${banner.imageUrls}")
            }
        }
    }
    fun bind(item: Operation) {
        binding.apply {
            banner = item
            executePendingBindings()
        }
    }
}
