package com.hanmajid.android.pokemonapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * [FavoriteFragment]'s ViewModel class.
 */
@KoinViewModel
class FavoriteViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    /**
     * Favorite Pokemon's Flow.
     */
    val favoritePokemonFlow =
        pokemonRepository.getFavoritePokemonPager().flow.cachedIn(viewModelScope)

    /**
     * Removes Pokemon from Favorite.
     */
    fun removePokemonFromFavorite(pokemonId: Int) {
        viewModelScope.launch {
            pokemonRepository.removePokemonFromFavorite(pokemonId)
        }
    }
}
