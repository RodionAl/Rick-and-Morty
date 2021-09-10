package com.example.rickandmorty.feature.main.episode.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.AllEpisodes
import com.example.domain.entities.Episode
import com.example.domain.interactors.IApiInteractor
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("LongLogTag")
class EpisodeViewModel @Inject constructor(
    private val episodeInteractor: IApiInteractor
) : BaseViewModel() {
    val liveData = MutableLiveData<AllEpisodes>()
    val liveDataEpisode = MutableLiveData<Episode>()

    init {
        episodeInteractor.getAllEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("EpisodeViewModel - error", it.toString())
            }
    }

    fun getAllEpisodesByPage(url: String) {
        episodeInteractor.getAllEpisodes(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("EpisodeViewModel - error", it.toString())
            }
    }

    fun getEpisodeByUrl(url: String) {
        episodeInteractor.getEpisode(url)
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
        Injector.clearEpisodeFragmentComponent()
    }
}
