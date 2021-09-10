package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.location.model.LocationDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LocationDetailFragmentModule.InnerLocationDetailViewModelModule::class])
class LocationDetailFragmentModule {

    @Module
    interface InnerLocationDetailViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(LocationDetailViewModel::class)
        fun bindMainViewModel(viewModel: LocationDetailViewModel): ViewModel
    }
}