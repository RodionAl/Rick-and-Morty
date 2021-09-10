package com.example.rickandmorty.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.database.DatabaseStorage
import com.example.data.database.dao.RickMortyDao
import com.example.data.repository.Repository
import com.example.domain.interactors.ApiInteractor
import com.example.domain.interactors.IApiInteractor
import com.example.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.InnerAppModule::class])
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideDatabaseStorage(): DatabaseStorage = Room.databaseBuilder(
        context,
        DatabaseStorage::class.java,
        DatabaseStorage.PEOPLE_DATA_BASE_STORAGE_NAME
    )
        .build()

    @Singleton
    @Provides
    fun providePeopleDao(databaseStorage: DatabaseStorage): RickMortyDao =
        databaseStorage.rickMortyDao

    @Module
    interface InnerAppModule {

        @Binds
        fun bindRepository(repository: Repository): IRepository

        @Binds
        fun bindPeopleInteractor(apiInteractor: ApiInteractor): IApiInteractor
    }
}