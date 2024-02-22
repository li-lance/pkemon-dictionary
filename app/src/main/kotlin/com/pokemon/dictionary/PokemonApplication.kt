package com.pokemon.dictionary

import android.app.Application
import com.pokemon.dictionary.di.netWorkModule
import com.pokemon.dictionary.di.pokemonViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PokemonApplication)
            modules(netWorkModule + pokemonViewModel)
        }
    }
}