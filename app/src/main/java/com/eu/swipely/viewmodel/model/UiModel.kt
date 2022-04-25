package com.eu.swipely.viewmodel.model

import com.eu.swipely.repository.local.model.CharacterEntity

sealed class UiModel {
    data class CharacterItem(val character: CharacterEntity) : UiModel()
}
