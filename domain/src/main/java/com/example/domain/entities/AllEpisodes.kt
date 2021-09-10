package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class AllEpisodes(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Episode>
)