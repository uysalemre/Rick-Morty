package com.eu.swipely.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.eu.swipely.repository.local.Database
import com.eu.swipely.repository.local.model.CharacterEntity
import com.eu.swipely.repository.local.model.CharacterKeysEntity
import com.eu.swipely.repository.mappers.mapToEntityModelList
import com.eu.swipely.repository.remote.service.CharacterService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PagedCacheMediator @Inject constructor(
    private val service: CharacterService,
    private val database: Database
) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val characterKeys = getKeyClosestToCurrentPosition(state)
                characterKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val characterKeys = getKeyForFirstItem(state)
                characterKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = characterKeys != null)
            }
            LoadType.APPEND -> {
                val characterKeys = getKeyForLastItem(state)
                characterKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = characterKeys != null)
            }
        }

        try {
            val apiResponse = service.getCharacters(page)
            val isResultEmpty = apiResponse.results.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.getCharacterKeysDao().clearCharacterKeys()
                    database.getCharacterDao().clearCharacters()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isResultEmpty) null else page + 1
                val characterKeys = apiResponse.results.map {
                    CharacterKeysEntity(it.id, prevKey, nextKey)
                }
                database.getCharacterKeysDao().insertCharacterKeys(characterKeys)
                database.getCharacterDao()
                    .insertCharacters(apiResponse.results.mapToEntityModelList())
            }
            return MediatorResult.Success(endOfPaginationReached = isResultEmpty)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyForLastItem(state: PagingState<Int, CharacterEntity>): CharacterKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                database.getCharacterKeysDao().getCharacterKeyById(character.id)
            }
    }

    private suspend fun getKeyForFirstItem(state: PagingState<Int, CharacterEntity>): CharacterKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                database.getCharacterKeysDao().getCharacterKeyById(character.id)
            }
    }

    private suspend fun getKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): CharacterKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.getCharacterKeysDao().getCharacterKeyById(id)
            }
        }
    }
}