package com.pokemon.dictionary.di

import com.pokemon.dictionary.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonViewModel = module {
    viewModel { MainViewModel(get()) }
}