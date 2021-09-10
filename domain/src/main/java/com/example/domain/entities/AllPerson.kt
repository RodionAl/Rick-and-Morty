package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class AllPerson(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Person>
)
