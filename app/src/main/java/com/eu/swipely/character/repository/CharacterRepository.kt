package com.eu.swipely.character.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.eu.swipely.character.repository.local.model.CharacterEntity
import com.eu.swipely.character.repository.mediator.PagedCacheMediator
import com.eu.swipely.character.repository.remote.service.CharacterService
import com.eu.swipely.utils.db.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterService,
    private val database: Database
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getAllCharacters(name: String) = Pager(
        config = PagingConfig(
            pageSize = 16,
            prefetchDistance = 2,
            initialLoadSize = 24,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { database.getCharacterListDao().getPagedCharacters() },
        remoteMediator = PagedCacheMediator(service, database, name)
    ).flow

    suspend fun removeAllCharacters() {
        database.getCharacterListDao().clearCharacters()
        database.getCharacterKeysDao().clearCharacterKeys()
    }

    fun getCharacterEpisodes(id: Int) = flow {
        emit(database.getCharacterListDao().getCharacterWithEpisodes(id))
    }.flowOn(Dispatchers.IO)

    fun updateCharacterFavStatus(character: CharacterEntity) = flow {
        emit(database.getCharacterListDao().updateFavStatus(character))
    }.flowOn(Dispatchers.IO)
}