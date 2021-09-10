package com.example.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.converter.ConverterString
import com.google.gson.annotations.SerializedName

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("url")
    val url: String,

    @Embedded(prefix = "location")
    @SerializedName("location")
    val location: Location,

    @Embedded(prefix = "origin")
    @SerializedName("origin")
    val origin: Origin,

    @SerializedName("episode")
    @TypeConverters(ConverterString::class)
    val episode: List<String>
)



