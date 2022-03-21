package com.hanmajid.android.pokemonapp.usecase

import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

/**
 * Use case of removing Pokemon from Favorite.
 */
@Factory
class RemovePokemonFromFavoriteUseCase(
    private val pokemonRepository: PokemonRepository,
) {

    /**
     * Run the use case.
     */
    fun run(coroutineScope: CoroutineScope, pokemonId: Int) {
        coroutineScope.launch {
            pokemonRepository.removePokemonFromFavorite(pokemonId)
        }
    }
}