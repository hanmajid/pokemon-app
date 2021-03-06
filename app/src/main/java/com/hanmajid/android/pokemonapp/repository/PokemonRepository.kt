package com.hanmajid.android.pokemonapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import com.hanmajid.android.pokemonapp.model.Pokemon

/**
 * Pokemon-related repository interface.
 */
interface PokemonRepository {

    /**
     * Gets a Pager that retrieves all Pokemons.
     */
    fun getAllPokemonPager(): Pager<Int, Pokemon>

    /**
     * Gets a Pager that retrieves all Pokemons in Favorite.
     */
    fun getFavoritePokemonPager(): Pager<Int, Pokemon>

    /**
     * Adds Pokemon with the given [pokemonId] to Favorite.
     */
    suspend fun addPokemonToFavorite(pokemonId: Int)

    /**
     * Removes Pokemon with the given [pokemonId] from Favorite.
     */
    suspend fun removePokemonFromFavorite(pokemonId: Int)

    /**
     * Gets [Pokemon] by the given [pokemonId].
     */
    fun getPokemonById(pokemonId: Int): LiveData<Pokemon?>

    /**
     * Gets [Pokemon] by the given [pokemonId].
     */
    suspend fun getPokemonDetailById(pokemonId: Int): Pokemon?
}