package com.example.rickandmorty.feature.main.episode

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Episode
import com.example.domain.entities.Person
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseFragment
import com.example.rickandmorty.feature.main.episode.model.EpisodeDetailViewModel
import com.example.rickandmorty.feature.main.person.PersonAdapter
import com.example.rickandmorty.feature.main.person.PersonsFragment

class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel>(),
    PersonAdapter.OnLoadNextPagePerson {

    lateinit var episodDate: Episode

    var listPerson = mutableListOf<Person>()
        set(value) {
            field = value
        }

    private lateinit var personAdapter: PersonAdapter
    private lateinit var onPersonClickListener: PersonsFragment.OnPersonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onPersonClickListener = context as PersonsFragment.OnPersonClickListener
        personAdapter = PersonAdapter(this) {
            onPersonClickListener.onPersonClicked(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_episode_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: TextView = view.findViewById(R.id.textName)
        val airDate: TextView = view.findViewById(R.id.textAirDate)
        val episode: TextView = view.findViewById(R.id.textEpisode)
        val recyclerViewCharacter: RecyclerView = view.findViewById(R.id.recyclerViewCharacter)
        val created: TextView = view.findViewById(R.id.textCreated)

        name.text = episodDate.name
        airDate.text = episodDate.airDate
        episode.text = episodDate.episode
        created.text = episodDate.created

        recyclerViewCharacter.layoutManager = GridLayoutManager(context, 2)
        recyclerViewCharacter.adapter = personAdapter
        personAdapter.personList = listPerson

        for (str in episodDate.characters) {
            viewModel.getPersonByUrl(str)
        }
        viewModel.liveDataOnePerson.observe(viewLifecycleOwner) {
            listPerson.add(it)
            personAdapter.personList = listPerson
        }

        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowHomeEnabled(true)
        ab?.title = "Episode details"
    }

    companion object {
        const val TAG = "FRAGMENT_EPISODE_DETAIL_TAG"
        fun newInstance() = EpisodeDetailFragment()
    }

    override fun onLoadNextPagePerson() {

    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.episodeDetailFragmentComponent?.inject(this)
    }
}