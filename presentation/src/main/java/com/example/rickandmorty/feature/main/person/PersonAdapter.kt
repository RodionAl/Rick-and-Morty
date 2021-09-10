package com.example.rickandmorty.feature.main.person

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Person
import com.example.rickandmorty.R
import com.squareup.picasso.Picasso

class PersonAdapter(
    private val listener: OnLoadNextPagePerson,
    private val onClick: (Person) -> Unit
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    var personList = mutableListOf<Person>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textSpecies: TextView = itemView.findViewById(R.id.textSpecies)
        val textStatus: TextView = itemView.findViewById(R.id.textStatus)
        val textGender: TextView = itemView.findViewById(R.id.textGender)
        val imageView: ImageView = itemView.findViewById(R.id.imageCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.textName.text = personList[position].name
        holder.textSpecies.text = personList[position].species
        holder.textStatus.text = personList[position].status
        holder.textGender.text = personList[position].gender
        Picasso.get().load(personList[position].image).into(holder.imageView)

        holder.itemView.setOnClickListener { onClick(personList[position]) }

        if (position == itemCount - 2) {
            listener.onLoadNextPagePerson()
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    interface OnLoadNextPagePerson {
        fun onLoadNextPagePerson()
    }
}