package com.example.rickandmorty.feature.main.person

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.Episode
import com.example.domain.entities.Person
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseFragment
import com.example.rickandmorty.feature.main.episode.EpisodeAdapter
import com.example.rickandmorty.feature.main.episode.EpisodesFragment
import com.example.rickandmorty.feature.main.location.LocationsFragment
import com.example.rickandmorty.feature.main.person.model.PersonDetailViewModel

class PersonDetailFragment : BaseFragment<PersonDetailViewModel>(),
    EpisodeAdapter.OnLoadNextPageEpisode {
    lateinit var person: Person

    var listEpisodes = mutableListOf<Episode>()
        set(value) {
            field = value
        }

    private lateinit var episodeAdapter: EpisodeAdapter
    private lateinit var onEpisodeClickListener: EpisodesFragment.OnEpisodeClickListener
    private lateinit var onLocationClickListener: LocationsFragment.OnLocationClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onEpisodeClickListener = context as EpisodesFragment.OnEpisodeClickListener
        episodeAdapter = EpisodeAdapter(this) {
            onEpisodeClickListener.onEpisodeClicked(it)
        }

        onLocationClickListener = context as LocationsFragment.OnLocationClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgPerson: ImageView = view.findViewById(R.id.imageCharacter)
        val name: TextView = view.findViewById(R.id.textName)
        val status: TextView = view.findViewById(R.id.textStatus)
        val species: TextView = view.findViewById(R.id.textSpecies)
        val type: TextView = view.findViewById(R.id.textType)
        val gender: TextView = view.findViewById(R.id.textGender)
        val textOriginNameTitle: TextView = view.findViewById(R.id.textOriginNameTitle)
        val originName: TextView = view.findViewById(R.id.textOriginName)
        val textLocationNameTitle: TextView = view.findViewById(R.id.textLocationNameTitle)
        val locationName: TextView = view.findViewById(R.id.textLocationName)
        val recyclerEpisodes: RecyclerView = view.findViewById(R.id.recyclerViewEpisode)
        val created: TextView = view.findViewById(R.id.textCreated)

        Glide.with(this).load(person.image).into(imgPerson)
        name.text = person.name
        status.text = person.status
        species.text = person.species
        type.text = person.type
        gender.text = person.gender
        created.text = person.created
        originName.text = person.origin.name
        locationName.text = person.location.name

        recyclerEpisodes.layoutManager = LinearLayoutManager(context)
        recyclerEpisodes.adapter = episodeAdapter
        episodeAdapter.episodesList = listEpisodes

        originName.setOnClickListener {
            onLocationClickListener.onLocationClickedByUrl(person.origin.url)
        }
        textOriginNameTitle.setOnClickListener {
            onLocationClickListener.onLocationClickedByUrl(person.origin.url)
        }

        locationName.setOnClickListener {
            onLocationClickListener.onLocationClickedByUrl(person.location.url)
        }
        textLocationNameTitle.setOnClickListener {
            onLocationClickListener.onLocationClickedByUrl(person.location.url)
        }

        for (str in person.episode) {
            viewModel.getEpisodeByUrl(str)
        }
        viewModel.liveDataEpisode.observe(viewLifecycleOwner) {
            listEpisodes.add(it)
            episodeAdapter.episodesList = listEpisodes
        }

        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowHomeEnabled(true)
        ab?.title = "Character details"

    }

    companion object {
        const val TAG = "FRAGMENT_PERSON_DETAIL_TAG"
        fun newInstance() = PersonDetailFragment()
    }

    override fun onLoadNextEpisode() {

    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.personDetailFragmentComponent?.inject(this)
    }
}