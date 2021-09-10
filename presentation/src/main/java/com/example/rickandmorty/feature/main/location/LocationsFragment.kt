package com.example.rickandmorty.feature.main.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Location
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseFragment
import com.example.rickandmorty.feature.main.location.model.LocationViewModel

class LocationsFragment : BaseFragment<LocationViewModel>(),
    LocationAdapter.OnLoadNextPageLocation {

    var listLocations = mutableListOf<Location>()
        set(value) {
            field = value
        }

    private var urlNext: String? = ""

    private lateinit var recycleView: RecyclerView
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var onLocationClickListener: OnLocationClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onLocationClickListener = context as OnLocationClickListener
        locationAdapter = LocationAdapter(this) {
            onLocationClickListener.onLocationClicked(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_location, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView = view.findViewById(R.id.recyclerViewLocation)
        recycleView.layoutManager = GridLayoutManager(context, 2)
        recycleView.adapter = locationAdapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            listLocations.addAll(it.results)
            urlNext = it.info.next
            locationAdapter.locationsList = listLocations
        }

        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        ab?.setDisplayHomeAsUpEnabled(false)
        ab?.setDisplayShowHomeEnabled(false)
        ab?.title = "Rick And Morty: locations"
    }

    interface OnLocationClickListener {
        fun onLocationClicked(location: Location)
        fun onLocationClickedByUrl(url: String)
    }

    companion object {
        const val TAG = "FRAGMENT_LIST_LOCATION_TAG"
        fun newInstance() = LocationsFragment()
    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.locationFragmentComponent?.inject(this)
    }

    override fun onLoadNextLocation() {
        if (urlNext != null) {
            viewModel.getAllLocationByPage(urlNext!!)
        }
    }
}