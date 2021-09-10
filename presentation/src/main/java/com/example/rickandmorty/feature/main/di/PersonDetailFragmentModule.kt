package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.person.model.PersonDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [PersonDetailFragmentModule.InnerPersonDetailViewModelModule::class])
class PersonDetailFragmentModule {

    @Module
    interface InnerPersonDetailViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(PersonDetailViewModel::class)
        fun bindMainViewModel(viewModel: PersonDetailViewModel): ViewModel
    }
}