package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.dao.RickMortyDao
import com.example.domain.converter.ConverterString
import com.example.domain.entities.*

@Database(
    entities = [Person::class],
//        Episode::class, Location::class,
//        AllEpisodes::class, AllLocations::class, AllPerson::class],
    version = 1
)
@TypeConverters(ConverterString::class)
abstract class DatabaseStorage: RoomDatabase() {

    abstract val rickMortyDao: RickMortyDao

    companion object{
        const val PEOPLE_DATA_BASE_STORAGE_NAME = "PEOPLE_DATA_BASE_STORAGE_NAME"
    }
}