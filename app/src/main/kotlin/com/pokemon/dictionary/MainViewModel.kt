package com.pokemon.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.dictionary.api.PokeApi
import kotlinx.coroutines.launch

class MainViewModel(private val api: PokeApi) : ViewModel() {
    suspend fun getPokemonList(offset:Int,limit:Int) = api.getPokemonList(offset, limit)
    suspend fun getPokemonDetail(name: String) = api.getPokemonDetail(name)

    fun test(){
        viewModelScope.launch {
            api.getPokemonList(0, 20)
        }
    }
}