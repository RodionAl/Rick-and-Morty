package com.example.rickandmorty.feature.main.location.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.AllLocations
import com.example.domain.entities.Location
import com.example.domain.interactors.IApiInteractor
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("LongLogTag")
class LocationViewModel @Inject constructor(
    private val locationInteractor: IApiInteractor
) : BaseViewModel() {
    val liveData = MutableLiveData<AllLocations>()
    val liveDataOneLocation = MutableLiveData<Location>()

    init {
        locationInteractor.getAllLocation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("LocationViewModel - error", it.toString())
            }
    }

    fun getAllLocationByPage(url: String) {
        locationInteractor.getAllLocation(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("LocationViewModel - error", it.toString())
            }
    }

    fun getLocationByUrl(url: String) {
        locationInteractor.getLocation(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataOneLocation.value = it
            }) {
                Log.e("LocationViewModel - error", it.toString())
            }
    }

    override fun onCleared() {
        super.onCleared()

        Injector.clearLocationFragmentComponent()
    }
}