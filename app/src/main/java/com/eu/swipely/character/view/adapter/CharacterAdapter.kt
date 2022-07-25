package com.eu.swipely.character.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eu.swipely.R
import com.eu.swipely.character.repository.local.model.CharacterEntity
import com.eu.swipely.databinding.ItemCardBinding

class CharacterAdapter(
    private val onClickItem: (id: Int) -> Unit,
    private val onClickFav: (character: CharacterEntity) -> Unit
) : PagingDataAdapter<CharacterEntity, CharacterAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object :
            DiffUtil.ItemCallback<CharacterEntity>() {
            override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
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
            binding.root.setOnClickListener {
                onClickItem(item.id)
            }
            binding.ivFavorite.setOnClickListener {
                onClickFav(item)
            }
        }
    }
}
