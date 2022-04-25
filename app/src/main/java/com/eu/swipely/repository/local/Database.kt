package com.eu.swipely.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eu.swipely.repository.local.dao.CharacterKeysDao
import com.eu.swipely.repository.local.dao.CharactersDao
import com.eu.swipely.repository.local.model.CharacterEntity
import com.eu.swipely.repository.local.model.CharacterKeysEntity

@Database(
    entities = [CharacterEntity::class, CharacterKeysEntity::class],
    version = 1, exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun getCharacterDao(): CharactersDao

    abstract fun getCharacterKeysDao(): CharacterKeysDao
}