package com.hanmajid.android.pokemonapp.data.grapql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.LoggingInterceptor
import com.hanmajid.android.pokemonapp.GetPokemonQuery
import com.hanmajid.android.pokemonapp.model.Pokemon
import org.koin.core.annotation.Single

/**
 * Default implementation of [GraphQLService].
 */
@Single
class GraphQLServiceImpl : GraphQLService {
    private var apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
        .addHttpInterceptor(LoggingInterceptor())
        .build()

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): GraphQLServiceResult<List<Pokemon>> {
        val response = apolloClient.query(
            GetPokemonQuery(limit, offset)
        ).execute()
        return if (response.hasErrors()) {
            GraphQLServiceResult(
                success = false,
                error = response.errors?.firstOrNull()?.message ?: "Unknown error",
            )
        } else {
            GraphQLServiceResult(
                success = true,
                data = response.data?.pokemon_v2_pokemonspecies?.map {
                    Pokemon.fromJson(it)
                } ?: emptyList()
            )
        }
    }
}