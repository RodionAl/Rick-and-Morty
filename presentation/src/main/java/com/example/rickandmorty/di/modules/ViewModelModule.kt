package com.example.rickandmorty.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}