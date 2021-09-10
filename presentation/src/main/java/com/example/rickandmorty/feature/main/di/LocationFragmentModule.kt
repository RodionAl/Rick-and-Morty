package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.location.model.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LocationFragmentModule.InnerLocationViewModelModule::class])
class LocationFragmentModule {

    @Module
    interface InnerLocationViewModelModule{

        @Binds
        @IntoMap
        @ViewModelKey(LocationViewModel::class)
        fun bindMainViewModel(viewModel: LocationViewModel): ViewModel
    }
}
