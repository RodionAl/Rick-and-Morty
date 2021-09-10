package com.example.rickandmorty.feature.main.person

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Person
import com.example.rickandmorty.R
import com.example.rickandmorty.di.Injector
import com.example.rickandmorty.feature.base.BaseFragment
import com.example.rickandmorty.feature.main.person.model.PersonViewModel

class PersonsFragment : BaseFragment<PersonViewModel>(), PersonAdapter.OnLoadNextPagePerson {

    private var listPerson = mutableListOf<Person>()
        set(value) {
            field = value
        }
    private var urlNext: String? = ""

    private lateinit var recycleView: RecyclerView
    private lateinit var personAdapter: PersonAdapter
    private lateinit var onPersonClickListener: OnPersonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onPersonClickListener = context as OnPersonClickListener
        personAdapter = PersonAdapter(this) {
            onPersonClickListener.onPersonClicked(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_persons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView = view.findViewById(R.id.recyclerViewCharacter)
        recycleView.layoutManager = GridLayoutManager(context, 2)
        recycleView.adapter = personAdapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            listPerson.addAll(it.results)
            urlNext = it.info.next
            personAdapter.personList = listPerson
        }

        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        ab?.setDisplayHomeAsUpEnabled(false)
        ab?.setDisplayShowHomeEnabled(false)
        ab?.title = "Rick And Morty: characters"
    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectDependencies() {
        Injector.personFragmentComponent?.inject(this)
    }

    interface OnPersonClickListener {
        fun onPersonClicked(person: Person)
    }

    companion object {
        const val TAG = "FRAGMENT_LIST_CHARACTER_TAG"
        fun newInstance() = PersonsFragment()
    }

    override fun onLoadNextPagePerson() {
        if (urlNext != null) {
            viewModel.getAllPersonByPage(urlNext!!)
        }
    }
}