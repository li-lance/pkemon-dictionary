package com.pokemon.dictionary.api.client

import io.ktor.http.HttpStatusCode

sealed class PokemonBffResult<T> {
    data class Success<T>(val response: T) : PokemonBffResult<T>()
    sealed class Failure<T> : PokemonBffResult<T>() {

        /**
         * An unsuccessful response from the BFF including a localized
         * [error] describing the failure.
         *
         * @param error The localized error from the BFF backend
         */
        data class LocalizedError<T>(val error: ErrorResponse) : Failure<T>()

        /**
         * An unsuccessful response from the BFF which does not include
         * a localized [ErrorResponse].
         *
         * @param httpStatusCode The HTTP status code we've received from
         * the backend
         */
        data class HttpError<T>(val httpStatusCode: HttpStatusCode) : Failure<T>()

        /**
         * We received a response from the BFF which cannot be parsed.
         *
         * @param throwable The exception which was thrown during serialization
         */
        data class SerializationError<T>(val throwable: Throwable) : Failure<T>()

        /**
         * The network request failed because the  BFF is not reachable
         * or the device has a bad internet connection.
         *
         * @param throwable The exception which was thrown during the network
         * request
         */
        data class NetworkError<T>(val throwable: Throwable) : Failure<T>()

        /**
         * An error occurred during the attempt to communicate with the BFF.
         *
         * @param throwable The exception which was thrown during the network
         * request
         */
        data class GeneralError<T>(val throwable: Throwable) : Failure<T>()
    }
}