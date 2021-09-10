package com.example.rickandmorty.feature.main.di

import com.example.rickandmorty.di.FragmentScope
import com.example.rickandmorty.feature.main.person.PersonDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PersonDetailFragmentModule::class])
interface PersonDetailFragmentComponent {
    fun inject(personDetailFragment: PersonDetailFragment)
}