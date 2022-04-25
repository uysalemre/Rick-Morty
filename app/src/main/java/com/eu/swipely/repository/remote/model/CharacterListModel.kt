package com.eu.swipely.repository.remote.model

data class CharacterListModel(
    val info: InfoModel,
    val results: List<CharacterModel>
)