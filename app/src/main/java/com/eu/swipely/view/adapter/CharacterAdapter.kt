package com.eu.swipely.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eu.swipely.R
import com.eu.swipely.databinding.ItemCardBinding
import com.eu.swipely.repository.local.model.CharacterEntity

class CharacterAdapter :
    PagingDataAdapter<CharacterEntity, CharacterAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object :
            DiffUtil.ItemCallback<CharacterEntity>() {
            override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_card,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterEntity) {
            binding.character = item
        }
    }
}
