package com.example.pruebatecnicaedgarpina.data.remote

import com.example.pruebatecnicaedgarpina.data.entities.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.pruebatecnicaedgarpina.data.entities.RickAndMortyCharacter

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters() : Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<RickAndMortyCharacter>
}