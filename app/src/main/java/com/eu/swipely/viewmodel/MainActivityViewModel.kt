package com.eu.swipely.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.eu.swipely.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    characterRepository: Repository
) : ViewModel() {

    val pagingData = characterRepository.getAllCharacters().cachedIn(viewModelScope)
}