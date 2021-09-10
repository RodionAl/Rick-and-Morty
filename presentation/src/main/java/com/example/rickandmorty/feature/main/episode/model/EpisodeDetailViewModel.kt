package com.example.rickandmorty.feature.main.episode.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.Person
import com.example.domain.interactors.IApiInteractor
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private val episodeDetailInteractor: IApiInteractor
) : BaseViewModel() {
    val liveDataOnePerson = MutableLiveData<Person>()

    @SuppressLint("LongLogTag")
    fun getPersonByUrl(url: String) {
        episodeDetailInteractor.getPerson(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataOnePerson.value = it
            }) {
                Log.e("LocationViewModel - error", it.toString())
            }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearEpisodeDetailFragmentComponent()
    }
}