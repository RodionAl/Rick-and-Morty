package com.example.rickandmorty.feature.main.di

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.ViewModelKey
import com.example.rickandmorty.feature.main.episode.model.EpisodeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [EpisodeDetailFragmentModule.InnerEpisodeDetailViewModelModule::class])
class EpisodeDetailFragmentModule {
    @Module
    interface InnerEpisodeDetailViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(EpisodeDetailViewModel::class)
        fun bindMainViewModel(viewModel: EpisodeDetailViewModel): ViewModel
    }
}