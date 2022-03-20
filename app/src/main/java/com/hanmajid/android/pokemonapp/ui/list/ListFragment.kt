package com.hanmajid.android.pokemonapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hanmajid.android.pokemonapp.databinding.FragmentListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * List of Pokemon screen.
 */
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
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
            viewModel.addPokemonToFavorite(it)
        }, {
            viewModel.removePokemonFromFavorite(it)
        }, {
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
        pokemonAdapter.refresh()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.allPokemonFlow.collectLatest { pagingData ->
                pokemonAdapter.submitData(pagingData)
            }
        }
    }

    companion object {
        @Suppress("unused")
        private const val TAG = "ListFragment"
    }
}