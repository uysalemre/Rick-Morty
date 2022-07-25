package com.eu.swipely.character.repository.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterAndEpisodeEntity(
    @Embedded
    val character: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val episodes: List<CharacterEpisodesEntity>
)