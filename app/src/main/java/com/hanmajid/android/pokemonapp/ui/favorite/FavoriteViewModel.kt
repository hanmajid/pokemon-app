package com.hanmajid.android.pokemonapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import com.hanmajid.android.pokemonapp.usecase.RemovePokemonFromFavoriteUseCase
import org.koin.android.annotation.KoinViewModel

/**
 * [FavoriteFragment]'s ViewModel class.
 */
@KoinViewModel
class FavoriteViewModel(
    pokemonRepository: PokemonRepository,
    private val removePokemonFromFavoriteUseCase: RemovePokemonFromFavoriteUseCase,
) : ViewModel() {
    /**
     * Whether Favorite Pokemon is empty.
     */
    val isEmpty = MutableLiveData(false)

    /**
     * Favorite Pokemon's Flow.
     */
    val favoritePokemonFlow =
        pokemonRepository.getFavoritePokemonPager().flow.cachedIn(viewModelScope)

    /**
     * Removes Pokemon from Favorite.
     */
    fun removePokemonFromFavorite(pokemonId: Int) {
        removePokemonFromFavoriteUseCase.run(viewModelScope, pokemonId)
    }
}
