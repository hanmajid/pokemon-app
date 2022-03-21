package com.hanmajid.android.pokemonapp.usecase

import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

/**
 * Use case of adding Pokemon to Favorite.
 */
@Factory
class AddPokemonToFavoriteUseCase(
    private val pokemonRepository: PokemonRepository,
) {

    /**
     * Run the use case.
     */
    fun run(coroutineScope: CoroutineScope, pokemonId: Int) {
        coroutineScope.launch {
            pokemonRepository.addPokemonToFavorite(pokemonId)
        }
    }
}