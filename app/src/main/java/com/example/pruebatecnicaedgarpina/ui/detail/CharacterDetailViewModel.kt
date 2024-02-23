package com.example.pruebatecnicaedgarpina.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.pruebatecnicaedgarpina.data.entities.RickAndMortyCharacter
import com.example.pruebatecnicaedgarpina.data.repository.CharacterRepository
import com.example.pruebatecnicaedgarpina.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val rickAndMortyCharacter: LiveData<Resource<RickAndMortyCharacter>> = _character


    fun start(id: Int) {
        _id.value = id
    }
}