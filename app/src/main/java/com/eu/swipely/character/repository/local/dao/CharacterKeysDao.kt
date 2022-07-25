package com.eu.swipely.character.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eu.swipely.character.repository.local.model.CharacterKeysEntity

@Dao
interface CharacterKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterKeys(keys: List<CharacterKeysEntity>)

    @Query("SELECT * FROM tb_character_keys WHERE characterId=:id")
    suspend fun getCharacterKeyById(id: Int): CharacterKeysEntity?

    @Query("DELETE FROM tb_character_keys")
    suspend fun clearCharacterKeys()
}