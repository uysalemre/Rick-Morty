package com.eu.swipely.character.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_character_keys")
data class CharacterKeysEntity(
    @PrimaryKey val characterId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)