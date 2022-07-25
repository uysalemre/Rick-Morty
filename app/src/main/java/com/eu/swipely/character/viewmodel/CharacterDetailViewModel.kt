package com.eu.swipely.character.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eu.swipely.character.repository.CharacterRepository
import com.eu.swipely.character.repository.local.model.CharacterAndEpisodeEntity
import com.eu.swipely.character.repository.local.model.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _characterAndEpisodes: MutableLiveData<CharacterAndEpisodeEntity> =
        MutableLiveData()
    val characterAndEpisodes: LiveData<CharacterAndEpisodeEntity> get() = _characterAndEpisodes

    fun getCharacterEpisodes(id: Int) {
        viewModelScope.launch {
            characterRepository.getCharacterEpisodes(id).collect {
                _characterAndEpisodes.value = it
            }
        }
    }

    fun addOrRemoveItemFromFav(character: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.updateCharacterFavStatus(character).collectLatest {
                getCharacterEpisodes(character.id)
            }
        }
    }
}