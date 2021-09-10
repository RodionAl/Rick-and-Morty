package com.example.rickandmorty.feature.main.di

import com.example.rickandmorty.di.FragmentScope
import com.example.rickandmorty.feature.main.episode.EpisodesFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [EpisodeFragmentModule::class])
interface EpisodeFragmentComponent {
    fun inject(episodesFragment: EpisodesFragment)
}