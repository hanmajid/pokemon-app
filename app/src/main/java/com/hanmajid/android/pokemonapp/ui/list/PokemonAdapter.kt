package com.hanmajid.android.pokemonapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hanmajid.android.pokemonapp.databinding.ItemPokemonBinding
import com.hanmajid.android.pokemonapp.model.Pokemon

/**
 * [Pokemon] list adapter.
 */
class PokemonAdapter(
    private val onAddToFavorite: (pokemonId: Int) -> Unit,
    private val onRemoveFromFavorite: (pokemonId: Int) -> Unit,
    private val onClickCard: (imageView: ImageView, pokemonId: Int) -> Unit,
) : PagingDataAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonComparator) {

    companion object {
        object PokemonComparator : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position), onAddToFavorite, onRemoveFromFavorite, onClickCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    /**
     * [Pokemon] list ViewHolder class.
     */
    class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            pokemon: Pokemon?,
            onAddToFavorite: (pokemonId: Int) -> Unit,
            onRemoveFromFavorite: (pokemonId: Int) -> Unit,
            onClickCard: (imageView: ImageView, pokemonId: Int) -> Unit,
        ) {
            pokemon?.apply {
                binding.pokemon = this
                val pokemonTypes = this.types
                binding.recyclerViewTypes.adapter = PokemonTypeAdapter().apply {
                    submitList(pokemonTypes)
                }
                binding.buttonAddFavorite.setOnClickListener {
                    onAddToFavorite(this.id)
                }
                binding.buttonRemoveFavorite.setOnClickListener {
                    onRemoveFromFavorite(this.id)
                }
                binding.cardContainer.setOnClickListener {
                    onClickCard(binding.cardImage, this.id)
                }
                ViewCompat.setTransitionName(binding.cardImage, "image-$id")
            }
        }

        companion object {
            fun create(parent: ViewGroup): PokemonViewHolder {
                val binding = ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                return PokemonViewHolder(binding)
            }
        }
    }
}