package com.hanmajid.android.pokemonapp.data.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.LoggingInterceptor
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.hanmajid.android.pokemonapp.BuildConfig
import com.hanmajid.android.pokemonapp.GetPokemonDetailQuery
import com.hanmajid.android.pokemonapp.GetPokemonQuery
import com.hanmajid.android.pokemonapp.model.Pokemon
import com.hanmajid.android.pokemonapp.model.PokemonDetail
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

    override suspend fun getAllPokemon(
        limit: Int,
        offset: Int
    ): GraphQLServiceResult<List<Pokemon>> {
        try {
            val response = apolloClient.query(
                GetPokemonQuery(limit, offset)
            ).execute()
            return if (response.hasErrors()) {
                val errorMessage = response.errors?.firstOrNull()?.message
                FirebaseCrashlytics.getInstance().recordException(Exception(errorMessage))
                GraphQLServiceResult(
                    success = false,
                    error = errorMessage ?: "Unknown error",
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
            FirebaseCrashlytics.getInstance().recordException(e)
            return GraphQLServiceResult(
                success = false,
                error = e.message ?: "Unknown error",
            )
        }
    }

    override suspend fun getDetailPokemon(pokemonId: Int): GraphQLServiceResult<Pokemon> {
        try {
            val response = apolloClient.query(
                GetPokemonDetailQuery(pokemonId)
            ).execute()
            if (response.hasErrors()) {
                val errorMessage = response.errors?.firstOrNull()?.message
                FirebaseCrashlytics.getInstance().recordException(Exception(errorMessage))
                return GraphQLServiceResult(
                    success = false,
                    error = errorMessage ?: "Unknown error",
                )
            } else {
                val json = response.data?.pokemon_v2_pokemonspecies?.firstOrNull()
                return if (json == null) {
                    val errorMessage = "Pokemon detail is empty"
                    FirebaseCrashlytics.getInstance().recordException(Exception(errorMessage))
                    GraphQLServiceResult(
                        success = false,
                        error = errorMessage,
                    )
                } else {
                    GraphQLServiceResult(
                        success = true,
                        data = Pokemon.fromJsonDetail(json)
                    )
                }
            }
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
            return GraphQLServiceResult(
                success = false,
                error = e.message ?: "Unknown error",
            )
        }
    }
}