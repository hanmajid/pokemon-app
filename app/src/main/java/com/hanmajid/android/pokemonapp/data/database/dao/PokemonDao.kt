package com.hanmajid.android.pokemonapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanmajid.android.pokemonapp.model.Pokemon
import com.hanmajid.android.pokemonapp.model.PokemonAbility
import com.hanmajid.android.pokemonapp.model.PokemonDetail
import com.hanmajid.android.pokemonapp.model.PokemonEvolution

/**
 * [Pokemon] table DAO class.
 */
@Dao
interface PokemonDao {
    /**
     * Insert all [Pokemon] to database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<Pokemon>)

    /**
     * Gets PagingSource for all Pokemons.
     */
    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, Pokemon>

    /**
     * Gets PagingSource for all Favorite Pokemons.
     */
    @Query("SELECT * FROM pokemons WHERE is_favorite = 1 ORDER BY id ASC")
    fun favoritePagingSource(): PagingSource<Int, Pokemon>

    /**
     * Delete all Pokemons with [Pokemon.isFavorite] == false from database.
     */
    @Query("DELETE FROM pokemons WHERE is_favorite = 0")
    suspend fun deleteAllNotFavorite()

    /**
     * Gets all Pokemons with [Pokemon.isFavorite] == true from database.
     */
    @Query("SELECT * FROM pokemons WHERE is_favorite = 1")
    suspend fun getAllFavorite(): List<Pokemon>

    /**
     * Update [Pokemon.isFavorite] with the given [pokemonId] to [newIsFavorite].
     */
    @Query("UPDATE pokemons SET is_favorite = :newIsFavorite WHERE id = :pokemonId")
    suspend fun updatePokemonIsFavorite(pokemonId: Int, newIsFavorite: Boolean)

    /**
     * Gets [Pokemon] by the given [id].
     */
    @Query("SELECT * FROM pokemons WHERE id = :id LIMIT 1")
    fun getById(id: Int): LiveData<Pokemon?>
}