package com.hanmajid.android.pokemonapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanmajid.android.pokemonapp.databinding.ItemPokemonEvolutionBinding
import com.hanmajid.android.pokemonapp.model.PokemonEvolution
import com.hanmajid.android.pokemonapp.util.NavigationUtil

/**
 * [PokemonEvolution] list adapter.
 */
class PokemonEvolutionAdapter(
    private val currentPokemonId: Int,
    private val navController: NavController
) :
    ListAdapter<PokemonEvolution, PokemonEvolutionAdapter.PokemonEvolutionViewHolder>(
        PokemonEvolutionComparator
    ) {

    companion object {
        object PokemonEvolutionComparator : DiffUtil.ItemCallback<PokemonEvolution>() {
            override fun areItemsTheSame(
                oldItem: PokemonEvolution,
                newItem: PokemonEvolution
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonEvolution,
                newItem: PokemonEvolution
            ): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onBindViewHolder(holder: PokemonEvolutionViewHolder, position: Int) {
        holder.bind(getItem(position), currentPokemonId, navController)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonEvolutionViewHolder {
        return PokemonEvolutionViewHolder.create(parent)
    }

    /**
     * [PokemonEvolution] list ViewHolder class.
     */
    class PokemonEvolutionViewHolder(private val binding: ItemPokemonEvolutionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            pokemonEvolution: PokemonEvolution?,
            currentPokemonId: Int,
            navController: NavController,
        ) {
            pokemonEvolution?.apply {
                binding.pokemonEvolution = this
                if (currentPokemonId != this.id) {
                    ViewCompat.setTransitionName(binding.image, "image-$id")
                    binding.container.setOnClickListener {
                        NavigationUtil.navigateToDetailFragment(
                            navController,
                            binding.image,
                            this.id,
                        )
                    }
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): PokemonEvolutionViewHolder {
                val binding = ItemPokemonEvolutionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                return PokemonEvolutionViewHolder(binding)
            }
        }
    }
}