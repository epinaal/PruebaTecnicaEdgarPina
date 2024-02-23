package com.example.pruebatecnicaedgarpina.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
): BaseDataSource() {

    suspend fun getCharacters() = getResult { api.getAllCharacters() }
    suspend fun getCharacter(id: Int) = getResult { api.getCharacter(id) }
}