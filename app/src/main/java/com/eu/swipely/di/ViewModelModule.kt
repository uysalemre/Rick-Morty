package com.eu.swipely.di

import com.eu.swipely.character.repository.CharacterRepository
import com.eu.swipely.character.repository.remote.service.CharacterService
import com.eu.swipely.utils.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideCharacterListRepository(services: CharacterService, database: Database) =
        CharacterRepository(services, database)
}