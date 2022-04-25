package com.eu.swipely.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eu.swipely.R
import com.eu.swipely.databinding.ItemLoadingBinding

class LoadingAndErrorStateAdapter(private val onRetry: () -> Unit) :
    LoadStateAdapter<LoadingAndErrorStateAdapter.LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState) {
            onRetry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_loading,
                parent,
                false
            )
        )

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState, onRetry: View.OnClickListener) {
            binding.state = when (loadState) {
                is LoadState.NotLoading -> 0
                LoadState.Loading -> 2
                is LoadState.Error -> 1
            }
            binding.onClickRetry = onRetry
        }
    }
}