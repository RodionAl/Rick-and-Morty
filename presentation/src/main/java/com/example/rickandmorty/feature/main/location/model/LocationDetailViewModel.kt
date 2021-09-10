package com.example.rickandmorty.feature.main.location.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.AllLocations
import com.example.domain.entities.Location
import com.example.domain.entities.Person
import com.example.domain.interactors.IApiInteractor
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("LongLogTag")
class LocationDetailViewModel @Inject constructor(
    private val locationDetailInteractor: IApiInteractor
) : BaseViewModel() {
    val liveData = MutableLiveData<AllLocations>()
    val liveDataOneLocation = MutableLiveData<Location>()
    val liveDataOnePerson = MutableLiveData<Person>()

    init {
        locationDetailInteractor.getAllLocation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }) {
                Log.e("LocationViewModel - error", it.toString())
            }
    }

    fun getPersonByUrl(url: String) {
        locationDetailInteractor.getPerson(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataOnePerson.value = it
            }) {
                Log.e("LocationViewModel - error", it.toString())
            }
    }

    fun getLocationByUrl(url: String) {
        locationDetailInteractor.getLocation(url)
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

        Injector.clearLocationDetailFragmentComponent()
    }

}