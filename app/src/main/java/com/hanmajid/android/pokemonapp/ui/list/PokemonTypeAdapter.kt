package com.hanmajid.android.pokemonapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanmajid.android.pokemonapp.databinding.ItemPokemonTypeBinding
import com.hanmajid.android.pokemonapp.model.PokemonType

/**
 * [PokemonType] list adapter.
 */
class PokemonTypeAdapter :
    ListAdapter<PokemonType, PokemonTypeAdapter.PokemonTypeViewHolder>(PokemonTypeComparator) {

    companion object {
        object PokemonTypeComparator : DiffUtil.ItemCallback<PokemonType>() {
            override fun areItemsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onBindViewHolder(holder: PokemonTypeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeViewHolder {
        return PokemonTypeViewHolder.create(parent)
    }

    /**
     * [PokemonType] list ViewHolder class.
     */
    class PokemonTypeViewHolder(private val binding: ItemPokemonTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonType: PokemonType?) {
            pokemonType?.apply {
                binding.pokemonType = this
                val (backgroundColor, foregroundColor) = this.getColorResources()
                binding.cardContainer.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        backgroundColor,
                    )
                )
                binding.textName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        foregroundColor,
                    )
                )
            }
        }

        companion object {
            fun create(parent: ViewGroup): PokemonTypeViewHolder {
                val binding = ItemPokemonTypeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                return PokemonTypeViewHolder(binding)
            }
        }
    }
}