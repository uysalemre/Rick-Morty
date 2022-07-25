package com.eu.swipely.utils.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eu.swipely.character.repository.local.dao.CharacterDao
import com.eu.swipely.character.repository.local.dao.CharacterKeysDao
import com.eu.swipely.character.repository.local.model.CharacterEntity
import com.eu.swipely.character.repository.local.model.CharacterEpisodesEntity
import com.eu.swipely.character.repository.local.model.CharacterKeysEntity

@Database(
    entities = [CharacterEntity::class, CharacterKeysEntity::class, CharacterEpisodesEntity::class],
    version = 1, exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun getCharacterListDao(): CharacterDao

    abstract fun getCharacterKeysDao(): CharacterKeysDao
}