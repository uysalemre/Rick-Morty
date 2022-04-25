package com.eu.swipely.repository.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eu.swipely.repository.local.model.CharacterEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM tb_character")
    fun getPagedCharacters(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM tb_character WHERE id IN (SELECT id from tb_character limit 5) ")
    suspend fun clearSingleCharacter()

    @Query("DELETE FROM tb_character")
    suspend fun clearCharacters()
}