package com.eu.swipely.character.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eu.swipely.character.repository.CharacterRepository
import com.eu.swipely.character.repository.local.model.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _pagingData: MutableLiveData<PagingData<CharacterEntity>> =
        MutableLiveData()
    val pagingData: LiveData<PagingData<CharacterEntity>> get() = _pagingData

    private val _isSearchInputAvailable: MutableLiveData<Boolean> = MutableLiveData()
    val isSearchInputAvailable: LiveData<Boolean> get() = _isSearchInputAvailable

    init {
        filterPaging("")
    }

    fun filterPaging(nameInput: String) {
        viewModelScope.launch {
            when {
                nameInput.isEmpty() -> {
                    _isSearchInputAvailable.value = false
                    characterRepository.getAllCharacters("").cachedIn(viewModelScope)
                        .collectLatest {
                            _pagingData.value = it
                        }
                }
                else -> {
                    _isSearchInputAvailable.value = true
                    characterRepository.removeAllCharacters()
                    characterRepository.getAllCharacters(nameInput).collectLatest {
                        _pagingData.value = it
                    }
                }
            }
        }
    }

    fun addOrRemoveItemFromFav(character: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.updateCharacterFavStatus(character).collect()
        }
    }
}