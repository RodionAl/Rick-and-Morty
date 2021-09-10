package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.person.model.PersonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [PersonFragmentModule.InnerPersonViewModelModule::class])
class PersonFragmentModule {

    @Module
    interface InnerPersonViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(PersonViewModel::class)
        fun bindMainViewModel(viewModel: PersonViewModel): ViewModel
    }
}