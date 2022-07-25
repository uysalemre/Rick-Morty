package com.eu.swipely.utils.mappers

import com.eu.swipely.character.repository.local.model.CharacterEntity
import com.eu.swipely.character.repository.local.model.CharacterEpisodesEntity
import com.eu.swipely.character.repository.remote.model.CharacterModel

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
                it.image,
                false
            )
        }
        else -> throw IllegalArgumentException("No Model Like That")
    }
} as List<R>


inline fun <reified T, R> List<T>.mapCharacterEpisodes(): List<R> = flatMap { character ->
    when (character) {
        is CharacterModel -> {
            character.episode.map { episode ->
                CharacterEpisodesEntity(
                    0,
                    episode,
                    character.id
                )
            }
        }
        else -> throw IllegalArgumentException("No Model Like That")
    }
} as List<R>