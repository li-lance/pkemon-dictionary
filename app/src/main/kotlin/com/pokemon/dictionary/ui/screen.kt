package com.pokemon.dictionary.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pokemon.dictionary.Screen
import com.pokemon.dictionary.ui.screen.PokemonDetailScreen
import com.pokemon.dictionary.ui.screen.PokemonListScreen
import com.pokemon.dictionary.ui.screen.PokemonMainScreen

@Composable
fun RouterNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PokemonMain.route) {
        composable(Screen.PokemonMain.route) {
            PokemonMainScreen(navController)
        }
        composable(Screen.PokemonList.route) {
            PokemonListScreen(navController)
        }
        composable(Screen.PokemonDetail.route) {
            PokemonDetailScreen(navController)
        }
    }
}