package com.example.rickandmorty.feature.main.episode

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Episode
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseFragment
import com.example.rickandmorty.feature.main.episode.model.EpisodeViewModel

class EpisodesFragment : BaseFragment<EpisodeViewModel>(), EpisodeAdapter.OnLoadNextPageEpisode {

    var listEpisodes = mutableListOf<Episode>()
        set(value) {
            field = value
        }
    private var urlNext: String? = ""

    private lateinit var recycleView: RecyclerView
    private lateinit var episodeAdapter: EpisodeAdapter
    private lateinit var onEpisodeClickListener: OnEpisodeClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onEpisodeClickListener = context as OnEpisodeClickListener
        episodeAdapter = EpisodeAdapter(this) {
            onEpisodeClickListener.onEpisodeClicked(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_episode, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView = view.findViewById(R.id.recyclerViewEpisode)
        recycleView.layoutManager = GridLayoutManager(context, 2)
        recycleView.adapter = episodeAdapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            listEpisodes.addAll(it.results)
            urlNext = it.info.next
            episodeAdapter.episodesList = listEpisodes
        }

        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        ab?.setDisplayHomeAsUpEnabled(false)
        ab?.setDisplayShowHomeEnabled(false)
        ab?.title = "Rick And Morty: episodes"
    }

    interface OnEpisodeClickListener {
        fun onEpisodeClicked(episode: Episode)
    }

    companion object {
        const val TAG = "FRAGMENT_LIST_EPISODE_TAG"
        fun newInstance() = EpisodesFragment()
    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.episodeFragmentComponent?.inject(this)
    }

    override fun onLoadNextEpisode() {
        if (urlNext != null) {
            viewModel.getAllEpisodesByPage(urlNext!!)
        }
    }
}