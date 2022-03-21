package com.hanmajid.android.pokemonapp.data.graphql

import com.hanmajid.android.pokemonapp.model.Pokemon

/**
 * GraphQL service interface.
 */
interface GraphQLService {

    /**
     * Gets all Pokemon from GraphQL service with the given [limit] and [offset].
     */
    suspend fun getAllPokemon(limit: Int, offset: Int): GraphQLServiceResult<List<Pokemon>>

    /**
     * Gets detail of Pokemon from GraphQL service with the given [pokemonId].
     */
    suspend fun getDetailPokemon(pokemonId: Int): GraphQLServiceResult<Pokemon>
}