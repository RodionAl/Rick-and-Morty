package com.example.rickandmorty.feature.main.di

import com.example.rickandmorty.di.ActivityScope
import com.example.rickandmorty.feature.main.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}