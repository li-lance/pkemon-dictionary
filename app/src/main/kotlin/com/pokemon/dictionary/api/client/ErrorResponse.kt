package com.pokemon.dictionary.api.client
class ErrorResponse(code: Int, message: String) : Throwable("($code) $message")