package com.hanmajid.android.pokemonapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanmajid.android.pokemonapp.repository.PokemonRepository
import com.hanmajid.android.pokemonapp.usecase.AddPokemonToFavoriteUseCase
import com.hanmajid.android.pokemonapp.usecase.RemovePokemonFromFavoriteUseCase
import com.hanmajid.android.pokemonapp.util.PokemonUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * [DetailFragment]'s ViewModel class.
 */
@KoinViewModel
class DetailViewModel(
    private val pokemonRepository: PokemonRepository,
    private val addPokemonToFavoriteUseCase: AddPokemonToFavoriteUseCase,
    private val removePokemonFromFavoriteUseCase: RemovePokemonFromFavoriteUseCase,
) : ViewModel() {
    var pokemonId = MutableLiveData<Int?>(null)
    val pokemon = Transformations.switchMap(pokemonId) {
        pokemonRepository.getPokemonById(it ?: 0)
    }

    val pokemonOfficialArtwork = Transformations.map(pokemonId) {
        PokemonUtil.getOfficialArtworkImageUrl(it ?: 0)
    }
    val pokemonIdFormatted = Transformations.map(pokemonId) {
        PokemonUtil.getIdFormatted(it ?: 0)
    }


    /**
     * Setups the ViewModel.
     */
    fun setup(_pokemonId: Int) {
        pokemonId.value = _pokemonId
        refreshPokemonDetail(_pokemonId)
    }

    /**
     * Adds Pokemon to Favorite.
     */
    fun addPokemonToFavorite(pokemonId: Int) {
        addPokemonToFavoriteUseCase.run(viewModelScope, pokemonId)
    }

    /**
     * Removes Pokemon from Favorite.
     */
    fun removePokemonFromFavorite(pokemonId: Int) {
        removePokemonFromFavoriteUseCase.run(viewModelScope, pokemonId)
    }

    fun refresh() {
        pokemonId.value?.let {
            refreshPokemonDetail(it)
        }
    }

    val isLoading = MutableLiveData(false)
    val loadingError = MutableLiveData<String?>(null)
    private fun refreshPokemonDetail(pokemonId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonDetail = pokemonRepository.getPokemonDetailById(pokemonId)
            isLoading.postValue(false)
            if (pokemonDetail == null) {
                loadingError.postValue("Failed loading Pokemon detail")
            }
        }
    }
}