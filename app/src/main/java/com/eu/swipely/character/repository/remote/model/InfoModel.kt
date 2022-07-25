package com.eu.swipely.character.repository.remote.model

data class InfoModel(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)