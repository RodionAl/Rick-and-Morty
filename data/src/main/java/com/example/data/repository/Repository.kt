package com.example.data.repository

import com.example.data.database.dao.RickMortyDao
import com.example.data.network.PersonApi
import com.example.domain.repository.IRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val rickMortyDao: RickMortyDao,
    private val personApi: PersonApi
) : IRepository {

    override fun getAllPerson() = personApi.getAllPerson()
    override fun getAllPerson(url: String) = personApi.getAllPerson(url)
//        .onErrorResumeNext {
//            rickMortyDao.getAllPerson()
//        }

    override fun getPerson(url: String) = personApi.getPerson(url)
//        .onErrorResumeNext {
//            rickMortyDao.getPerson(id)
//        }

    override fun getAllEpisodes() = personApi.getAllEpisodes()
    override fun getAllEpisodes(url: String) = personApi.getAllEpisodes(url)

    override fun getEpisode(url: String) = personApi.getEpisode(url)

    override fun getAllLocation() = personApi.getAllLocation()
    override fun getAllLocation(url: String) = personApi.getAllLocation(url)

    override fun getLocation(url: String) = personApi.getLocation(url)
}