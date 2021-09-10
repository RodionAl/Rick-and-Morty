package com.example.domain.repository

import com.example.domain.entities.*
import io.reactivex.rxjava3.core.Single

interface IRepository {

    fun getAllPerson(): Single<AllPerson>

    fun getAllPerson(url: String): Single<AllPerson>

    fun getPerson(url: String): Single<Person>

    fun getAllEpisodes(): Single<AllEpisodes>

    fun getAllEpisodes(url: String): Single<AllEpisodes>

    fun getEpisode(url: String): Single<Episode>

    fun getAllLocation(): Single<AllLocations>

    fun getAllLocation(url: String): Single<AllLocations>

    fun getLocation(url: String): Single<Location>
}