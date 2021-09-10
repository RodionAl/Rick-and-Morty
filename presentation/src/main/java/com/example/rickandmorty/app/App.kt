package com.example.rickandmorty.app

import android.app.Application
import com.example.rickandmorty.di.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.initApplicationComponent(this)
    }
}