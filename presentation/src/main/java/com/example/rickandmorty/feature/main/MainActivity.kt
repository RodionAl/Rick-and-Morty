package com.example.rickandmorty.feature.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.domain.entities.Episode
import com.example.domain.entities.Location
import com.example.domain.entities.Person
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseActivity
import com.example.rickandmorty.feature.main.episode.EpisodeDetailFragment
import com.example.rickandmorty.feature.main.episode.EpisodesFragment
import com.example.rickandmorty.feature.main.location.LocationDetailFragment
import com.example.rickandmorty.feature.main.location.LocationsFragment
import com.example.rickandmorty.feature.main.person.PersonDetailFragment
import com.example.rickandmorty.feature.main.person.PersonsFragment
import com.example.rickandmorty.feature.main.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<MainViewModel>(), PersonsFragment.OnPersonClickListener,
    EpisodesFragment.OnEpisodeClickListener, LocationsFragment.OnLocationClickListener {

    private lateinit var fragmentListPerson: PersonsFragment
    private lateinit var fragmentListLocation: LocationsFragment
    private lateinit var fragmentListEpisode: EpisodesFragment
    private lateinit var navView: BottomNavigationView

    private var backPrassedTime: Long = 0
    private var backToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_RickAndMorty)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().run {
            fragmentListPerson = PersonsFragment.newInstance()
            replace(
                R.id.fragment_activity_main,
                fragmentListPerson,
                PersonsFragment.TAG
            )
            commit()
        }

        navView = findViewById(R.id.navView)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.characterFragment -> {
                    supportFragmentManager.beginTransaction().run {
                        fragmentListPerson = PersonsFragment.newInstance()
                        replace(
                            R.id.fragment_activity_main,
                            fragmentListPerson,
                            PersonsFragment.TAG
                        )
                        commit()
                    }
                    true
                }
                R.id.locationFragment -> {
                    supportFragmentManager.beginTransaction().run {
                        fragmentListLocation = LocationsFragment.newInstance()
                        replace(
                            R.id.fragment_activity_main,
                            fragmentListLocation,
                            LocationsFragment.TAG
                        )
                        commit()
                    }
                    true
                }
                R.id.episodeFragment -> {
                    supportFragmentManager.beginTransaction().run {
                        fragmentListEpisode = EpisodesFragment.newInstance()
                        replace(
                            R.id.fragment_activity_main,
                            fragmentListEpisode,
                            EpisodesFragment.TAG
                        )
                        commit()
                    }
                    true
                }
                else -> false
            }
        }

    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.mainActivityComponent?.inject(this)
    }

    override fun onPersonClicked(person: Person) {
        supportFragmentManager.beginTransaction().run {
            val fragmentPersonDetail = PersonDetailFragment.newInstance()
            fragmentPersonDetail.person = person
            replace(
                R.id.fragment_activity_main,
                fragmentPersonDetail,
                PersonDetailFragment.TAG
            )
            addToBackStack(PersonDetailFragment.TAG)
            commit()
        }
        navView.visibility = View.GONE
    }

    override fun onEpisodeClicked(episode: Episode) {
        supportFragmentManager.beginTransaction().run {
            val fragmentEpisodeDetail = EpisodeDetailFragment.newInstance()
            fragmentEpisodeDetail.episodDate = episode
            replace(
                R.id.fragment_activity_main,
                fragmentEpisodeDetail,
                EpisodeDetailFragment.TAG
            )
            addToBackStack(EpisodeDetailFragment.TAG)
            commit()
        }
        navView.visibility = View.GONE
    }

    override fun onLocationClicked(location: Location) {
        supportFragmentManager.beginTransaction().run {
            val fragmentLocationDetail = LocationDetailFragment.newInstance()
            fragmentLocationDetail.locationDate = location
            replace(
                R.id.fragment_activity_main,
                fragmentLocationDetail,
                LocationDetailFragment.TAG
            )
            addToBackStack(LocationDetailFragment.TAG)
            commit()
        }
        navView.visibility = View.GONE
    }

    override fun onLocationClickedByUrl(url: String) {
        supportFragmentManager.beginTransaction().run {
            val fragmentLocationDetail = LocationDetailFragment.newInstance()
            fragmentLocationDetail.strUrl = url
            replace(
                R.id.fragment_activity_main,
                fragmentLocationDetail,
                LocationDetailFragment.TAG
            )
            addToBackStack(LocationDetailFragment.TAG)
            commit()
        }
        navView.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
            if (supportFragmentManager.backStackEntryCount == 0) {
                navView.visibility = View.VISIBLE
            }
        } else if (supportFragmentManager.backStackEntryCount == 0) {
            if (backPrassedTime + 2000 > System.currentTimeMillis()) {
                backToast!!.cancel()
                super.onBackPressed()
                return
            } else {
                backToast = Toast.makeText(
                    baseContext, R.string.exit,
                    Toast.LENGTH_SHORT
                )
                backToast!!.show()
            }
            backPrassedTime = System.currentTimeMillis()
        }
    }
}