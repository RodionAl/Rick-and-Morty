package com.example.rickandmorty.feature.main.location

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
import com.example.domain.entities.Location
import com.example.domain.entities.Person
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseFragment
import com.example.rickandmorty.feature.main.location.model.LocationDetailViewModel
import com.example.rickandmorty.feature.main.person.PersonAdapter
import com.example.rickandmorty.feature.main.person.PersonsFragment

class LocationDetailFragment : BaseFragment<LocationDetailViewModel>(),
    PersonAdapter.OnLoadNextPagePerson {
    var locationDate: Location? = null

    var listPerson = mutableListOf<Person>()
        set(value) {
            field = value
        }

    var strUrl: String? = null

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
    ) = inflater.inflate(R.layout.fragment_location_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: TextView = view.findViewById(R.id.textName)
        val type: TextView = view.findViewById(R.id.textType)
        val dimension: TextView = view.findViewById(R.id.textDimension)
        val recyclerViewCharacter: RecyclerView = view.findViewById(R.id.recyclerViewCharacter)
        val created: TextView = view.findViewById(R.id.textCreated)
        if (strUrl != null) {
            viewModel.getLocationByUrl(strUrl!!)
        }
        viewModel.liveDataOneLocation.observe(viewLifecycleOwner) {
            locationDate = it
            name.text = locationDate?.name
            type.text = locationDate?.type
            dimension.text = locationDate?.dimension
            created.text = locationDate?.created

            if (locationDate != null) {
                for (str in locationDate!!.residents) {
                    viewModel.getPersonByUrl(str)
                }
                viewModel.liveDataOnePerson.observe(viewLifecycleOwner) {
                    listPerson.add(it)
                    personAdapter.personList = listPerson
                }
            }
        }

        name.text = locationDate?.name
        type.text = locationDate?.type
        dimension.text = locationDate?.dimension
        created.text = locationDate?.created

        recyclerViewCharacter.layoutManager = GridLayoutManager(context, 2)
        recyclerViewCharacter.adapter = personAdapter
        personAdapter.personList = listPerson

        if (locationDate != null) {
            for (str in locationDate!!.residents) {
                viewModel.getPersonByUrl(str)
            }
            viewModel.liveDataOnePerson.observe(viewLifecycleOwner) {
                listPerson.add(it)
                personAdapter.personList = listPerson
            }
        }

        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowHomeEnabled(true)
        ab?.title = "Location details"
    }

    companion object {
        const val TAG = "FRAGMENT_Location_DETAIL_TAG"
        fun newInstance() = LocationDetailFragment()
    }

    override fun onLoadNextPagePerson() {

    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.locationDetailFragmentComponent?.inject(this)
    }
}