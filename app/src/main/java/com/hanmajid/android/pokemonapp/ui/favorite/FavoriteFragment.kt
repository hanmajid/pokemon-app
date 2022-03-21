package com.hanmajid.android.pokemonapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.hanmajid.android.pokemonapp.databinding.FragmentFavoriteBinding
import com.hanmajid.android.pokemonapp.ui.detail.DetailFragment
import com.hanmajid.android.pokemonapp.ui.list.PokemonAdapter
import com.hanmajid.android.pokemonapp.util.NavigationUtil
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
        setupTransition()
    }

    /**
     * Setups the list adapter.
     */
    private fun setupAdapter() {
        pokemonAdapter = PokemonAdapter({
            // Do nothing
        }, {
            viewModel.removePokemonFromFavorite(it)
        }, { transitioningView, pokemonId ->
            NavigationUtil.navigateToDetailFragment(
                findNavController(),
                transitioningView,
                pokemonId,
            )
        })
    }

    /**
     * Setups the page's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

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
        pokemonAdapter.addLoadStateListener {
            pokemonAdapter.itemCount.let {
                viewModel.isEmpty.postValue(it <= 0)
            }
        }
    }


    /**
     * Setups the page's transition.
     */
    private fun setupTransition() {
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)

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
        private const val TAG = "FavoriteFragment"
    }
}