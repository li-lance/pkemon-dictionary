package com.pokemon.dictionary.di

import com.pokemon.dictionary.api.PokeApi
import com.pokemon.dictionary.api.client.pokemonHttpClient
import org.koin.dsl.module

val netWorkModule = module {
    single<PokeApi> { PokeApi(pokemonHttpClient) }
}