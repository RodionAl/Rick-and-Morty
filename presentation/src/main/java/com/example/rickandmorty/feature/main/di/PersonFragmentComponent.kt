package com.example.rickandmorty.feature.main.di

import com.example.rickandmorty.di.FragmentScope
import com.example.rickandmorty.feature.main.person.PersonsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PersonFragmentModule::class])
interface PersonFragmentComponent {
    fun inject(personFragment: PersonsFragment)
}