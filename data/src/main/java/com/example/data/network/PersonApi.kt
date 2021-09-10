package com.example.data.network

import com.example.domain.entities.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface PersonApi {

    @GET(ALL_CHARACTERS)
    fun getAllPerson(): Single<AllPerson>

    @GET
    fun getAllPerson(@Url url: String): Single<AllPerson>

    @GET
    fun getPerson(@Url url: String): Single<Person>

    @GET(ALL_EPISODES)
    fun getAllEpisodes(): Single<AllEpisodes>

    @GET
    fun getAllEpisodes(@Url url: String): Single<AllEpisodes>

    @GET
    fun getEpisode(@Url url: String): Single<Episode>

    @GET(ALL_LOCATIONS)
    fun getAllLocation(): Single<AllLocations>

    @GET
    fun getAllLocation(@Url url: String): Single<AllLocations>

    @GET
    fun getLocation(@Url url: String): Single<Location>

    companion object {
        const val ALL_CHARACTERS = "character/"
        const val ALL_EPISODES = "episode/"
        const val ALL_LOCATIONS = "location/"
    }
}