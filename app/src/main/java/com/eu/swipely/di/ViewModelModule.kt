package com.eu.swipely.di

import com.eu.swipely.repository.Repository
import com.eu.swipely.repository.local.Database
import com.eu.swipely.repository.remote.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideSwipelyRepository(services: CharacterService, database: Database) =
        Repository(services, database)

}