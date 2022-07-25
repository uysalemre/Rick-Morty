package com.eu.swipely.character.repository.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.eu.swipely.character.repository.local.model.CharacterAndEpisodeEntity
import com.eu.swipely.character.repository.local.model.CharacterEntity
import com.eu.swipely.character.repository.local.model.CharacterEpisodesEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<CharacterEpisodesEntity>)

    @Query("SELECT * FROM tb_character WHERE id=:id")
    suspend fun getCharacterWithEpisodes(id: Int): CharacterAndEpisodeEntity

    @Query("SELECT * FROM tb_character")
    fun getPagedCharacters(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM tb_character")
    suspend fun clearCharacters()

    @Update
    suspend fun updateFavStatus(character: CharacterEntity)
}