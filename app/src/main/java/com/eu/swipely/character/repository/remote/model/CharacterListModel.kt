package com.eu.swipely.character.repository.remote.model

data class CharacterListModel(
    val info: InfoModel,
    val results: List<CharacterModel>
)