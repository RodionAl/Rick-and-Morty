package com.example.rickandmorty.feature.main.di

import com.example.rickandmorty.di.FragmentScope
import com.example.rickandmorty.feature.main.location.LocationDetailFragment
import com.example.rickandmorty.feature.main.location.LocationsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [LocationDetailFragmentModule::class])
interface LocationDetailFragmentComponent {
    fun inject(locationDetailFragment: LocationDetailFragment)
}