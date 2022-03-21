package com.hanmajid.android.pokemonapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import com.hanmajid.android.pokemonapp.usecase.AddPokemonToFavoriteUseCase
import com.hanmajid.android.pokemonapp.usecase.RemovePokemonFromFavoriteUseCase
import org.koin.android.annotation.KoinViewModel

/**
 * [ListFragment]'s ViewModel class.
 */
@KoinViewModel
class ListViewModel(
    pokemonRepository: PokemonRepository,
    private val addPokemonToFavoriteUseCase: AddPokemonToFavoriteUseCase,
    private val removePokemonFromFavoriteUseCase: RemovePokemonFromFavoriteUseCase,
) : ViewModel() {

    /**
     * All Pokemon's Flow.
     */
    val allPokemonFlow = pokemonRepository.getAllPokemonPager().flow.cachedIn(viewModelScope)

    /**
     * Adds Pokemon to Favorite.
     */
    fun addPokemonToFavorite(pokemonId: Int) {
        addPokemonToFavoriteUseCase.run(viewModelScope, pokemonId)
    }

    /**
     * Removes Pokemon from Favorite.
     */
    fun removePokemonFromFavorite(pokemonId: Int) {
        removePokemonFromFavoriteUseCase.run(viewModelScope, pokemonId)
    }
}