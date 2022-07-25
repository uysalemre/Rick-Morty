package com.eu.swipely.character.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eu.swipely.R
import com.eu.swipely.character.repository.local.model.CharacterEpisodesEntity
import com.eu.swipely.databinding.ItemEpisodeBinding

class EpisodesAdapter :
    ListAdapter<CharacterEpisodesEntity, EpisodesAdapter.EpisodeViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<CharacterEpisodesEntity>() {
            override fun areItemsTheSame(
                oldItem: CharacterEpisodesEntity,
                newItem: CharacterEpisodesEntity
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterEpisodesEntity,
                newItem: CharacterEpisodesEntity
            ) = oldItem == newItem
        }
    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterEpisodesEntity) {
            binding.episode = item.episode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_episode,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}