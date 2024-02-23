package com.pokemon.dictionary.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pokemon.dictionary.Screen

@Composable
fun PokemonMainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android", navController = navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavHostController) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            navController.navigate(Screen.PokemonList.route)
        }
    )
}