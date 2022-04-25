package com.eu.swipely.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.eu.swipely.repository.local.Database
import com.eu.swipely.repository.mediator.PagedCacheMediator
import com.eu.swipely.repository.remote.service.CharacterService
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: CharacterService,
    private val database: Database
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getAllCharacters() = Pager(
        config = PagingConfig(
            pageSize = 6,
            prefetchDistance = 2,
            initialLoadSize = 8,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { database.getCharacterDao().getPagedCharacters() },
        remoteMediator = PagedCacheMediator(service, database)
    ).flow

}


