package com.eu.swipely.character.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val gender: String,
    val species: String,
    val location: String,
    val name: String,
    val status: String,
    val image: String,
    val isFav : Boolean
)


