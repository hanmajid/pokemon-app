package com.hanmajid.android.pokemonapp.util

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.hanmajid.android.pokemonapp.R
import com.hanmajid.android.pokemonapp.ui.detail.DetailFragment

/**
 * Navigation-related utility class.
 */
object NavigationUtil {

    /**
     * Navigates to [DetailFragment] screen with the given [pokemonId] as a parameter.
     *
     * This method also sets the transition animation between the fragments.
     */
    fun navigateToDetailFragment(
        navController: NavController,
        transitioningView: View,
        pokemonId: Int,
    ) {
        navController.navigate(
            R.id.action_global_detailFragment,
            Bundle().apply {
                putInt("pokemonId", pokemonId)
            },
            null,
            FragmentNavigatorExtras(transitioningView to transitioningView.transitionName)
        )
    }
}