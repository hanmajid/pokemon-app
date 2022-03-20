package com.hanmajid.android.pokemonapp.data.grapql

import com.hanmajid.android.pokemonapp.model.Pokemon

/**
 * GraphQL service interface.
 */
interface GraphQLService {

    /**
     * Gets all Pokemon from GraphQL service with the given [limit] and [offset].
     */
    suspend fun getPokemonList(limit: Int, offset: Int): GraphQLServiceResult<List<Pokemon>>
}