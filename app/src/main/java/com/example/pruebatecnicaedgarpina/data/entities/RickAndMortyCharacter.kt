package com.example.pruebatecnicaedgarpina.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class RickAndMortyCharacter(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
