package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Person
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RickMortyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePerson(person: Person): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPerson(persons: List<Person>): Completable

    @Query("SELECT * FROM person")
    fun getAllPerson(): Single<List<Person>>

    @Query("SELECT * FROM person WHERE id =:id")
    fun getPerson(id: Int): Single<Person>
}
