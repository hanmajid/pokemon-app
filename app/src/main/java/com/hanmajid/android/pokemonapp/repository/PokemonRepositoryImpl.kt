package com.hanmajid.android.pokemonapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.hanmajid.android.pokemonapp.data.database.MainDatabase
import com.hanmajid.android.pokemonapp.data.graphql.GraphQLService
import com.hanmajid.android.pokemonapp.data.remoteMediator.AllPokemonRemoteMediator
import com.hanmajid.android.pokemonapp.model.Pokemon
import org.koin.core.annotation.Single

/**
 * Default implementation of [PokemonRepository].
 */
@Single
@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl(
    private val allPokemonRemoteMediator: AllPokemonRemoteMediator,
    private val graphQLService: GraphQLService,
    private val mainDatabase: MainDatabase,
) : PokemonRepository {
    override fun getAllPokemonPager(): Pager<Int, Pokemon> {
        return Pager(
            config = PagingConfig(AllPokemonRemoteMediator.PAGING_LOAD_SIZE),
            remoteMediator = allPokemonRemoteMediator,
        ) {
            mainDatabase.pokemonDao().pagingSource()
        }
    }

    override fun getFavoritePokemonPager(): Pager<Int, Pokemon> {
        return Pager(
            config = PagingConfig(FAVORITE_PAGING_LOAD_SIZE)
        ) {
            mainDatabase.pokemonDao().favoritePagingSource()
        }
    }

    override suspend fun addPokemonToFavorite(pokemonId: Int) {
        return mainDatabase.pokemonDao().updatePokemonIsFavorite(pokemonId, true)
    }

    override suspend fun removePokemonFromFavorite(pokemonId: Int) {
        return mainDatabase.pokemonDao().updatePokemonIsFavorite(pokemonId, false)
    }

    override fun getPokemonById(pokemonId: Int): LiveData<Pokemon?> {
        return mainDatabase.pokemonDao().getById(pokemonId)
    }

    override suspend fun getPokemonDetailById(pokemonId: Int): Pokemon? {
        val response = graphQLService.getDetailPokemon(pokemonId)
        return if (response.success && response.data != null) {
            val pokemon = response.data
            val favoritePokemons = mainDatabase.pokemonDao().getAllFavorite()
            val favoritePokemonIds = favoritePokemons.map { it.id }

            val insertedPokemon = if (favoritePokemonIds.contains(pokemon.id)) {
                pokemon.copy(isFavorite = true)
            } else {
                pokemon
            }
            mainDatabase.pokemonDao().insertAll(listOf(insertedPokemon))
            response.data
        } else {
            null
        }
    }

    companion object {
        const val FAVORITE_PAGING_LOAD_SIZE = 20
    }
}