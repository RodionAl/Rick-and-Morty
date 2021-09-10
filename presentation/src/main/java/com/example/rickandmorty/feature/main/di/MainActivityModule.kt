package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [MainActivityModule.InnerMainViewModelModule::class])
class MainActivityModule {

    @Module
    interface InnerMainViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun bindMainViewModel(viewModel: MainViewModel): ViewModel
    }
}