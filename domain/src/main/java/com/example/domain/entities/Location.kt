package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.converter.ConverterString
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location")
data class Location(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("created")
    val created: String,

    @SerializedName("residents")
    @TypeConverters(ConverterString::class)
    val residents: List<String>,

    @SerializedName("url")
    val url: String
)