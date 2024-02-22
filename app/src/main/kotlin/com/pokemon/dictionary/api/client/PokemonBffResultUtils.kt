package com.pokemon.dictionary.api.client

import io.ktor.client.call.DoubleReceiveException
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
}

internal inline fun <T> runCatchingOrError(block: () -> PokemonBffResult<T>): PokemonBffResult<T> =
    runCatching { block() }.getOrElse {
        when (it) {
            is SerializationException -> PokemonBffResult.Failure.SerializationError(it)
            else -> PokemonBffResult.Failure.NetworkError(it)
        }
    }

internal suspend inline fun <reified DTO> receivePokemonBffResult(
    block: () -> HttpResponse
): PokemonBffResult<DTO> = runCatchingOrError {
    val response = block()
    return if (response.status.isSuccess()) {
        // Try to parse the response
        try {
            PokemonBffResult.Success(response.body<DTO>())
        } catch (e: NoTransformationFoundException) {
            PokemonBffResult.Failure.SerializationError(e)
        } catch (e: DoubleReceiveException) {
            PokemonBffResult.Failure.NetworkError(e)
        }
    } else {
        try {
            PokemonBffResult.Failure.LocalizedError(
                json.decodeFromString(String(response.readBytes()))
            )
        } catch (e: SerializationException) {
            PokemonBffResult.Failure.HttpError(response.status)
        }
    }
}

internal fun <DTO> PokemonBffResult<DTO>.asFlow() = flow {
    emit(this@asFlow)
}

internal fun HttpRequestBuilder.addQueryParameters(vararg param: Pair<String, String>) {
    for ((key, value) in param) {
        url.parameters.append(key, value)
    }
}
