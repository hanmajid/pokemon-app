package com.hanmajid.android.pokemonapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hanmajid.android.pokemonapp.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Favorite Pokemon screen.
 */
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
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
        setupBinding()
    }

    /**
     * Setups the page's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    companion object {
        @Suppress("unused")
        private const val TAG = "FavoriteFragment"
    }
}