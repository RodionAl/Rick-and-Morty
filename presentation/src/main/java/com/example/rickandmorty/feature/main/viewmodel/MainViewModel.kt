package com.example.rickandmorty.feature.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.AllPerson
import com.example.domain.interactors.IApiInteractor
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val personInteractor: IApiInteractor
) : BaseViewModel() {
    val liveData = MutableLiveData<AllPerson>()

    init {
        personInteractor.getAllPerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                liveData.value = it
            }) {
                Log.e("MainViewModel - error", it.toString())
            }
    }

    override fun onCleared() {
        super.onCleared()

        Injector.clearMainActivityComponent()
    }
}