package com.example.pruebatecnicaedgarpina.data.repository

import com.example.pruebatecnicaedgarpina.data.local.CharacterDao
import com.example.pruebatecnicaedgarpina.data.remote.RemoteDataSource
import com.example.pruebatecnicaedgarpina.utils.getOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CharacterDao
) {

    fun getCharacter(id: Int) = getOperation(
        localDatabase = { localDataSource.getCharacter(id) },
        remoteCall = { remoteDataSource.getCharacter(id) },
        cacheResult = { localDataSource.insert(it) }
    )

    fun getAllCharacters() = getOperation(
        localDatabase = { localDataSource.getAllCharacters() },
        remoteCall = { remoteDataSource.getCharacters() },
        cacheResult = { localDataSource.insertAll(it.results) }
    )
}