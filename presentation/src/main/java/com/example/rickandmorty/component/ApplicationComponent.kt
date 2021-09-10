package com.example.rickandmorty.component

import android.content.Context
import com.example.rickandmorty.di.modules.AppModule
import com.example.rickandmorty.di.modules.NetworkModule
import com.example.rickandmorty.di.modules.ViewModelModule
import com.example.rickandmorty.feature.main.di.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {
    val context: Context
    val mainActivityComponent: MainActivityComponent
    val personFragmentComponent: PersonFragmentComponent
    val personDetailFragmentComponent: PersonDetailFragmentComponent
    val locationFragmentComponent: LocationFragmentComponent
    val locationDetailFragmentComponent: LocationDetailFragmentComponent
    val episodeFragmentComponent: EpisodeFragmentComponent
    val episodeDetailFragmentComponent: EpisodeDetailFragmentComponent

}