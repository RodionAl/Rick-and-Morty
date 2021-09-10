package com.example.domain.interactors

import com.example.domain.entities.*
import com.example.domain.repository.IRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface IApiInteractor {
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

class ApiInteractor @Inject constructor(private val repository: IRepository) : IApiInteractor {

    override fun getAllPerson() = repository.getAllPerson()
    override fun getAllPerson(url: String) = repository.getAllPerson(url)

    override fun getPerson(url: String) = repository.getPerson(url)

    override fun getAllEpisodes() = repository.getAllEpisodes()
    override fun getAllEpisodes(url: String) = repository.getAllEpisodes(url)

    override fun getEpisode(url: String) = repository.getEpisode(url)

    override fun getAllLocation() = repository.getAllLocation()
    override fun getAllLocation(url: String) = repository.getAllLocation(url)

    override fun getLocation(url: String) = repository.getLocation(url)
}