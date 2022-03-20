package com.hanmajid.android.pokemonapp.data.remoteMediator

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.hanmajid.android.pokemonapp.data.database.MainDatabase
import com.hanmajid.android.pokemonapp.data.grapql.GraphQLService
import com.hanmajid.android.pokemonapp.model.Pokemon
import org.koin.core.annotation.Single
import java.io.IOException

/**
 * [RemoteMediator] class that retrieves all Pokemons in pagination.
 */
@Single
@OptIn(ExperimentalPagingApi::class)
class AllPokemonRemoteMediator(
    private val graphQLService: GraphQLService,
    private val mainDatabase: MainDatabase,
) : RemoteMediator<Int, Pokemon>() {
    var currentIndex = 0

    companion object {
        const val PAGING_LOAD_SIZE = 20
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        return try {
            if (loadType == LoadType.REFRESH) {
                currentIndex = 0
            } else if (loadType == LoadType.PREPEND) {
                currentIndex = 0
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            val response = graphQLService.getPokemonList(
                limit = PAGING_LOAD_SIZE,
                offset = currentIndex * PAGING_LOAD_SIZE,
            )
            Log.wtf(
                "TAG",
                "Fetching $loadType limit=$PAGING_LOAD_SIZE & offset=${currentIndex}"
            )

            mainDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    mainDatabase.pokemonDao().deleteAllNotFavorite()
                }

                val favoritePokemons = mainDatabase.pokemonDao().getAllFavorite()
                val favoritePokemonIds = favoritePokemons.map { it.id }

                val insertedPokemons = response.data?.map {
                    if (favoritePokemonIds.contains(it.id)) {
                        it.copy(isFavorite = true)
                    } else {
                        it
                    }
                } ?: emptyList()

                mainDatabase.pokemonDao().insertAll(insertedPokemons)
            }

            currentIndex++
            MediatorResult.Success(
                endOfPaginationReached = response.data.isNullOrEmpty() || currentIndex > 4
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}