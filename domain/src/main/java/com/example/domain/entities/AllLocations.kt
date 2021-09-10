package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class AllLocations(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Location>
)