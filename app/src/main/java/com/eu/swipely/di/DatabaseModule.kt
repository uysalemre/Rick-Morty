package com.eu.swipely.di

import android.content.Context
import androidx.room.Room
import com.eu.swipely.utils.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, "swipely_db").build()
}