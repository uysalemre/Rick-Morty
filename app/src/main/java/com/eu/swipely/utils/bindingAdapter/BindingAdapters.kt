package com.eu.swipely.utils.bindingAdapter

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eu.swipely.R
import com.eu.swipely.character.repository.local.model.CharacterAndEpisodeEntity
import com.eu.swipely.character.repository.local.model.CharacterEntity
import com.eu.swipely.character.view.adapter.CharacterAdapter
import com.eu.swipely.character.view.adapter.EpisodesAdapter
import com.eu.swipely.character.view.adapter.LoadingAndErrorStateAdapter
import com.eu.swipely.character.viewmodel.CharacterDetailViewModel
import com.eu.swipely.character.viewmodel.CharacterViewModel
import com.eu.swipely.databinding.ItemLoadingBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.ic_text_widget_background_light)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(view)
}

@BindingAdapter("loadCharacterImage")
fun loadCharacterImage(view: ImageView, character: CharacterAndEpisodeEntity?) {
    character?.let {
        loadImage(view, character.character.image)
    }
}

@BindingAdapter("setCharacterText")
fun setCharacterText(view: TextView, character: CharacterAndEpisodeEntity?) {
    character?.let {
        view.text = when (view.id) {
            R.id.tvCharacterNameSurname -> character.character.name
            R.id.tvLastKnownLocation -> character.character.location
            R.id.tvCharacterStatus -> {
                view.background = when (character.character.status) {
                    "Alive" -> ContextCompat.getDrawable(view.context, R.drawable.ic_alive)
                    "Dead" -> ContextCompat.getDrawable(view.context, R.drawable.ic_dead)
                    else -> ContextCompat.getDrawable(view.context, R.drawable.ic_unknown)
                }
                character.character.status
            }
            R.id.tvCharacterSpecies -> character.character.species
            R.id.tvCharacterGender -> character.character.gender
            else -> ""
        }
    }
}

@BindingAdapter("setSimpleRv")
fun setSimpleRv(view: RecyclerView, character: CharacterAndEpisodeEntity?) {
    character?.let {
        if (view.adapter == null) {
            view.adapter = EpisodesAdapter()
        }
        (view.adapter as EpisodesAdapter).submitList(character.episodes)
    }
}

@BindingAdapter("setTintForFav")
fun setTintForFav(view: ImageButton, character: CharacterAndEpisodeEntity?) {
    character?.let {
        if (character.character.isFav) {
            view.backgroundTintList = ContextCompat.getColorStateList(view.context, R.color.green)
        } else {
            view.backgroundTintList = ContextCompat.getColorStateList(view.context, R.color.white)
        }
    }
}

@BindingAdapter(value = ["characterModel", "detailViewModel"], requireAll = true)
fun updateFavStat(
    view: ImageButton,
    character: CharacterAndEpisodeEntity?,
    detailViewModel: CharacterDetailViewModel
) {
    character?.let {
        view.setOnClickListener {
            detailViewModel.addOrRemoveItemFromFav(character.character.copy(isFav = !character.character.isFav))
        }
    }
}

@BindingAdapter(value = ["adapterVm", "fab"])
fun searchByName(view: SearchView, adapterVm: CharacterViewModel, fab: FloatingActionButton) {
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            adapterVm.filterPaging(p0 ?: "").also { fab.visibility = View.GONE }
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            return false
        }
    })
}

@BindingAdapter(value = ["adapterVm", "adapter", "emptyView", "data", "fab"], requireAll = true)
fun setAdapter(
    view: RecyclerView,
    adapterVm: CharacterViewModel,
    adapter: CharacterAdapter,
    emptyView: ItemLoadingBinding,
    data: PagingData<CharacterEntity>?,
    fab: FloatingActionButton
) {
    if (view.adapter == null) {
        val layoutManager = GridLayoutManager(view.context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == adapter.itemCount && adapter.itemCount > 0) {
                        2
                    } else {
                        1
                    }
                }
            }
        }
        view.layoutManager = layoutManager
        view.adapter = adapter.withLoadStateFooter(LoadingAndErrorStateAdapter { adapter.retry() })
        view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                when {
                    dy > 0 -> fab.visibility = View.VISIBLE
                    dy < 0 -> fab.visibility = View.GONE
                }
            }
        })
        fab.setOnClickListener {
            view.smoothScrollToPosition(0)
        }
        adapterVm.viewModelScope.launch {
            adapter.loadStateFlow.distinctUntilChangedBy { it.refresh }
                .collect {
                    if (it.refresh is LoadState.Error) {
                        emptyView.root.visibility = View.VISIBLE
                        view.visibility = View.GONE
                    } else {
                        emptyView.root.visibility = View.GONE
                        view.visibility = View.VISIBLE
                    }
                }
        }
    }

    data?.let {
        adapterVm.viewModelScope.launch {
            adapter.submitData(data)
        }
    }
}

@BindingAdapter(value = ["searchView", "viewModel"], requireAll = true)
fun removeSearch(
    view: TextView,
    searchView: SearchView,
    viewModel: CharacterViewModel
) {
    if (!view.hasOnClickListeners()) {
        view.setOnClickListener {
            searchView.setQuery("", true)
            viewModel.filterPaging("")
        }
    }
}
