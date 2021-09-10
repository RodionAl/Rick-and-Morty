package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.component.ApplicationComponent
import com.example.rickandmorty.component.DaggerApplicationComponent
import com.example.rickandmorty.di.modules.AppModule
import com.example.rickandmorty.feature.main.di.*

object Injector {

    lateinit var applicationComponent: ApplicationComponent
        private set

    var mainActivityComponent: MainActivityComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.mainActivityComponent
            }

            return field
        }

    fun clearMainActivityComponent() {
        mainActivityComponent = null
    }

    internal fun initApplicationComponent(context: Context) {
        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    var personFragmentComponent: PersonFragmentComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.personFragmentComponent
            }
            return field
        }

    fun clearPersonFragmentComponent() {
        personFragmentComponent = null
    }

    var personDetailFragmentComponent: PersonDetailFragmentComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.personDetailFragmentComponent
            }
            return field
        }

    fun clearPersonDetailFragmentComponent() {
        personDetailFragmentComponent = null
    }

    var locationFragmentComponent: LocationFragmentComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.locationFragmentComponent
            }
            return field
        }

    fun clearLocationFragmentComponent() {
        locationFragmentComponent = null
    }

    var locationDetailFragmentComponent: LocationDetailFragmentComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.locationDetailFragmentComponent
            }
            return field
        }

    fun clearLocationDetailFragmentComponent() {
        locationDetailFragmentComponent = null
    }

    var episodeFragmentComponent: EpisodeFragmentComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.episodeFragmentComponent
            }
            return field
        }

    fun clearEpisodeFragmentComponent() {
        episodeFragmentComponent = null
    }

    var episodeDetailFragmentComponent: EpisodeDetailFragmentComponent? = null
        private set
        get() {
            if (field == null) {
                field = applicationComponent.episodeDetailFragmentComponent
            }
            return field
        }

    fun clearEpisodeDetailFragmentComponent() {
        episodeDetailFragmentComponent = null
    }

}