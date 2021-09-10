package com.example.rickandmorty.feature.main.person.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.AllPerson
import com.example.domain.entities.Episode
import com.example.domain.interactors.IApiInteractor
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val personInteractor: IApiInteractor
) : BaseViewModel() {

    val liveData = MutableLiveData<AllPerson>()
    val liveDataEpisode = MutableLiveData<Episode>()

    init {
        personInteractor.getAllPerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("PersonViewModel - error", it.toString())
            }
    }

    fun getAllPersonByPage(url: String) {
        personInteractor.getAllPerson(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("PersonViewModel - error", it.toString())
            }
    }

    @SuppressLint("LongLogTag")
    fun getEpisodeByUrl(url: String) {
        personInteractor.getEpisode(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataEpisode.value = it
            }) {
                Log.e("EpisodeViewModel - error", it.toString())
            }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearPersonFragmentComponent()
    }
}