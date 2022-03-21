package com.hanmajid.android.pokemonapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hanmajid.android.pokemonapp.util.PokemonUtil
import org.koin.android.annotation.KoinViewModel

/**
 * [DetailFragment]'s ViewModel class.
 */
@KoinViewModel
class DetailViewModel : ViewModel() {
    val pokemonId = MutableLiveData<Int?>(null)

    val pokemonOfficialArtwork = Transformations.map(pokemonId) {
        if (it == null) {
            null
        } else {
            PokemonUtil.getOfficialArtworkImageUrl(it)
        }
    }

    /**
     * Setups the ViewModel.
     */
    fun setup(_pokemonId: Int) {
        pokemonId.value = _pokemonId
    }
}