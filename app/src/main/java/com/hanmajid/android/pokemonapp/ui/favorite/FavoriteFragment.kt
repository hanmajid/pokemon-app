package com.hanmajid.android.pokemonapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hanmajid.android.pokemonapp.databinding.FragmentFavoriteBinding
import com.hanmajid.android.pokemonapp.ui.list.ListFragmentDirections
import com.hanmajid.android.pokemonapp.ui.list.PokemonAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Favorite Pokemon screen.
 */
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupBinding()
        setupViewModel()
    }

    /**
     * Setups the list adapter.
     */
    private fun setupAdapter() {
        pokemonAdapter = PokemonAdapter({
            // Do nothing
        }, {
            viewModel.removePokemonFromFavorite(it)
        }, { _, it ->
            findNavController().navigate(
                ListFragmentDirections.actionGlobalDetailFragment(it)
            )
        })
    }

    /**
     * Setups the page's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pokemonAdapter
        }
    }

    /**
     * Setups the page's ViewModel.
     */
    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.favoritePokemonFlow.collectLatest { pagingData ->
                pokemonAdapter.submitData(pagingData)
            }
        }
    }

    companion object {
        @Suppress("unused")
        private const val TAG = "FavoriteFragment"
    }
}