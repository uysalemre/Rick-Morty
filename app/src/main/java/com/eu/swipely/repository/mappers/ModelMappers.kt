package com.eu.swipely.repository.mappers

import com.eu.swipely.repository.local.model.CharacterEntity
import com.eu.swipely.repository.remote.model.CharacterModel

inline fun <reified T, R> List<T>.mapToEntityModelList(): List<R> = map {
    when (it) {
        is CharacterModel -> {
            CharacterEntity(
                it.id,
                it.gender,
                it.species,
                it.location.name,
                it.name,
                it.status,
                it.image
            )
        }
        else -> throw IllegalArgumentException("No Model Like That")
    }
} as List<R>