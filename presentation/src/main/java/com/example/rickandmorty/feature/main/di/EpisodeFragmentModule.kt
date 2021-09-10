package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.episode.model.EpisodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [EpisodeFragmentModule.InnerEpisodeViewModelModule::class])
class EpisodeFragmentModule {
    @Module
    interface InnerEpisodeViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(EpisodeViewModel::class)
        fun bindMainViewModel(viewModel: EpisodeViewModel): ViewModel
    }
}