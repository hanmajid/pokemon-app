package com.hanmajid.android.pokemonapp.data.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.LoggingInterceptor
import com.hanmajid.android.pokemonapp.BuildConfig
import com.hanmajid.android.pokemonapp.GetPokemonQuery
import com.hanmajid.android.pokemonapp.model.Pokemon
import org.koin.core.annotation.Single

/**
 * Default implementation of [GraphQLService].
 */
@Single
class GraphQLServiceImpl : GraphQLService {
    private var apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl(BuildConfig.GRAPHQL_URL)
        .addHttpInterceptor(LoggingInterceptor())
        .build()

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): GraphQLServiceResult<List<Pokemon>> {
        try {
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
        } catch (e: Exception) {
            return GraphQLServiceResult(
                success = false,
                error = e.message ?: "Unknown error",
            )
        }
    }
}