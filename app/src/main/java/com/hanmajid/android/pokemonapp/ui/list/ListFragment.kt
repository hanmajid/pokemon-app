package com.hanmajid.android.pokemonapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hanmajid.android.pokemonapp.databinding.FragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * List of Pokemon screen.
 */
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
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
        setupBinding()
    }

    /**
     * Setups the page's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonTest.setOnClickListener {
            findNavController().navigate(
                ListFragmentDirections.actionGlobalDetailFragment(1)
            )
        }
    }

    companion object {
        @Suppress("unused")
        private const val TAG = "ListFragment"
    }
}