package com.jingtian.springrain.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.databinding.ListItemBannerBinding
import com.jingtian.springrain.helper.BANNER

class OperationAdapter(private val viewModel: HomeViewModel)
    : ListAdapter<Operation, RecyclerView.ViewHolder>(OperationDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            BANNER -> BannerViewHolder(ListItemBannerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            else -> TODO("not implemented")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val operation = getItem(position)
        when (operation.type){
            BANNER -> (holder as BannerViewHolder).bind(operation)
        }
    }

    class BannerViewHolder(private val binding: ListItemBannerBinding)
        : RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {
//                binding.banner?.let { banner ->
//                    TODO("not implemented")
//                }
            }
        }
        fun bind(item: Operation) {
            binding.apply {
                banner = item
                executePendingBindings()
            }
        }

    }
}

private class OperationDiffCallback : DiffUtil.ItemCallback<Operation>() {

    override fun areItemsTheSame(oldItem: Operation, newItem: Operation): Boolean {
        return oldItem.operationId == newItem.operationId
    }

    override fun areContentsTheSame(oldItem: Operation, newItem: Operation): Boolean {
        return oldItem == newItem
    }
}