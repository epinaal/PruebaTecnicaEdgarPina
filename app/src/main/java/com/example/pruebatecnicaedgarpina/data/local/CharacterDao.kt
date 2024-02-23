package com.example.pruebatecnicaedgarpina.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebatecnicaedgarpina.data.entities.RickAndMortyCharacter

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<RickAndMortyCharacter>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<RickAndMortyCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rickAndMortyCharacters: List<RickAndMortyCharacter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rickAndMortyCharacter: RickAndMortyCharacter)


}