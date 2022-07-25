package com.eu.swipely.character.repository.remote.service

import com.eu.swipely.character.repository.remote.model.CharacterListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") pageNumber: Int,
        @Query("name") name: String
    ): CharacterListModel
}