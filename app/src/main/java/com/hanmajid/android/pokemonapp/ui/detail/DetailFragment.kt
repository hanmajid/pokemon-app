package com.hanmajid.android.pokemonapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import com.hanmajid.android.pokemonapp.R
import com.hanmajid.android.pokemonapp.databinding.FragmentDetailBinding
import com.hanmajid.android.pokemonapp.util.PokemonUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Pokemon detail screen.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
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
        setupBinding()
        setupTransition()

        viewModel.setup(args.pokemonId)
    }

    /**
     * Setups the page's transition.
     */
    private fun setupTransition() {
        sharedElementEnterTransition = MaterialContainerTransform()
        ViewCompat.setTransitionName(binding.image, "image-${args.pokemonId}")
    }

    /**
     * Setups the page's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    companion object {
        @Suppress("unused")
        private const val TAG = "DetailFragment"
    }
}