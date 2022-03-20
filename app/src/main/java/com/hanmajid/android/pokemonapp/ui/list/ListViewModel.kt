package com.hanmajid.android.pokemonapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * [ListFragment]'s ViewModel class.
 */
@KoinViewModel
class ListViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    /**
     * All Pokemon's Flow.
     */
    val allPokemonFlow = pokemonRepository.getAllPokemonPager().flow.cachedIn(viewModelScope)

    /**
     * Adds Pokemon to Favorite.
     */
    fun addPokemonToFavorite(pokemonId: Int) {
        viewModelScope.launch {
            pokemonRepository.addPokemonToFavorite(pokemonId)
        }
    }

    /**
     * Removes Pokemon from Favorite.
     */
    fun removePokemonFromFavorite(pokemonId: Int) {
        viewModelScope.launch {
            pokemonRepository.removePokemonFromFavorite(pokemonId)
        }
    }
}