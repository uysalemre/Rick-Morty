package com.eu.swipely.character.repository.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_character_episodes",
    foreignKeys = [
        ForeignKey(
            CharacterEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("characterId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class CharacterEpisodesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val episode: String,
    @ColumnInfo(index = true)
    val characterId: Int
)
