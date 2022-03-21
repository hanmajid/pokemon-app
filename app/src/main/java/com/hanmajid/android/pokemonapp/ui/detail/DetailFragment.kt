package com.hanmajid.android.pokemonapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.hanmajid.android.pokemonapp.databinding.FragmentDetailBinding
import com.hanmajid.android.pokemonapp.model.PokemonEvolution
import com.hanmajid.android.pokemonapp.model.PokemonType
import com.hanmajid.android.pokemonapp.ui.list.PokemonTypeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Pokemon detail screen.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var pokemonTypeAdapter: PokemonTypeAdapter
    private lateinit var pokemonEvolutionAdapter: PokemonEvolutionAdapter

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        setupBinding()
        setupViewModel()
        setupTransition()

        viewModel.setup(args.pokemonId)
    }

    /**
     * Setups the [PokemonType], [PokemonEvolution] list adapter.
     */
    private fun setupAdapters() {
        pokemonTypeAdapter = PokemonTypeAdapter()
        pokemonEvolutionAdapter = PokemonEvolutionAdapter(args.pokemonId, findNavController())
    }

    /**
     * Setups the page's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerViewTypes.adapter = pokemonTypeAdapter
        binding.recyclerViewEvolutions.adapter = pokemonEvolutionAdapter
    }

    /**
     * Setups the page's ViewModel.
     */
    private fun setupViewModel() {
        viewModel.pokemon.observe(viewLifecycleOwner) {
            it?.apply {
                pokemonTypeAdapter.submitList(this.types)
                pokemonEvolutionAdapter.submitList(this.detail?.evolutions)
            }
        }
        viewModel.loadingError.observe(viewLifecycleOwner) {
            it?.apply {
                if (this.isNotEmpty()) {
                    Toast.makeText(requireContext(), this, Toast.LENGTH_SHORT).show()
                    viewModel.loadingError.postValue(null)
                }
            }
        }
    }

    /**
     * Setups the page's transition.
     */
    private fun setupTransition() {
        sharedElementEnterTransition = MaterialContainerTransform()
        ViewCompat.setTransitionName(binding.image, "image-${args.pokemonId}")

        /**
         * The below code is required to animate correctly when the user returns from [DetailFragment].
         */
        postponeEnterTransition()
        (requireView().parent as ViewGroup).viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    companion object {
        @Suppress("unused")
        private const val TAG = "DetailFragment"
    }
}