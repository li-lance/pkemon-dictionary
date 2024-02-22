package com.pokemon.dictionary.api

import com.pokemon.dictionary.api.client.addQueryParameters
import com.pokemon.dictionary.api.client.asFlow
import com.pokemon.dictionary.api.client.receivePokemonBffResult
import com.pokemon.dictionary.api.model.NamedApiResourceList
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class PokeApi(private val httpClient: HttpClient) {
    suspend fun getBerryList(offset: Int, limit: Int) =
        receivePokemonBffResult<NamedApiResourceList> {
            httpClient.get("berry") {
                addQueryParameters("offset" to offset.toString(), "limit" to limit.toString())
            }
        }.asFlow()

    suspend fun getPokemonList(offset: Int, limit: Int) =
        receivePokemonBffResult<NamedApiResourceList> {
            httpClient.get("pokemon") {
                addQueryParameters("offset" to offset.toString(), "limit" to limit.toString())
            }
        }.asFlow()

    suspend fun getPokemonDetail(name: String) =
        receivePokemonBffResult<String> {
            httpClient.get("pokemon/$name")
        }.asFlow()
}