package com.eu.swipely.character.repository.remote.model

data class CharacterModel(
    val gender: String,
    val species: String,
    val id: Int,
    val image: String,
    val location: LocationModel,
    val name: String,
    val status: String,
    val episode: List<String>
)