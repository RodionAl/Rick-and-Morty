package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.converter.ConverterString
import com.google.gson.annotations.SerializedName

@Entity(tableName = "person")
data class Episode(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("created")
    val created: String,

    @SerializedName("characters")
    @TypeConverters(ConverterString::class)
    val characters: List<String>,

    @SerializedName("url")
    val url: String
)