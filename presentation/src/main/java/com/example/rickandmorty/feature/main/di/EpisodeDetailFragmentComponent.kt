package com.example.rickandmorty.feature.main.di

import com.example.rickandmorty.di.FragmentScope
import com.example.rickandmorty.feature.main.episode.EpisodeDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [EpisodeDetailFragmentModule::class])
interface EpisodeDetailFragmentComponent {
    fun inject(episodeDetailFragment: EpisodeDetailFragment)
}