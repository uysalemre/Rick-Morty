package com.eu.swipely.repository.remote.service

import com.eu.swipely.repository.remote.model.CharacterListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character/")
    suspend fun getCharacters(@Query("page") pageNumber: Int): CharacterListModel
}