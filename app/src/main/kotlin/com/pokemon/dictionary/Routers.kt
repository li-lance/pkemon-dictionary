package com.pokemon.dictionary

object Routers {
    const val POKEMON_LIST = "pokemon_list"
    const val POKEMON_DETAIL = "pokemon_detail"
    const val POKEMON_MAIN = "pokemon_main"
    const val POKEMON_LOGIN = "pokemon_login"
}
sealed class Screen(val route: String) {
    data object PokemonList : Screen(route = Routers.POKEMON_LIST)
    data object PokemonDetail : Screen(route = Routers.POKEMON_DETAIL)
    data object PokemonMain : Screen(route = Routers.POKEMON_MAIN)
    data object PokemonLogin : Screen(route = Routers.POKEMON_LOGIN)
}