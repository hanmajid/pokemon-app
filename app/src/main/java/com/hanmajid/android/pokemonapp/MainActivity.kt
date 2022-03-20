package com.hanmajid.android.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.hanmajid.android.pokemonapp.databinding.ActivityMainBinding

/**
 * App's main activity.
 *
 * This app uses "single activity and multiple fragments" approach. This activity serves as
 * container for all fragments. Each fragment represents a screen in the app.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBinding()
    }

    /**
     * Setups the activity's DataBinding.
     */
    private fun setupBinding() {
        binding.lifecycleOwner = this
        setupToolbar()
        setupBottomNavigation()
    }

    /**
     * Setups the toolbar (top app bar).
     */
    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds)
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
    }

    /**
     * Setups the Bottom Navigation View.
     */
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setupWithNavController(navController)

        // Only shows BottomNav on top level destinations.
        navController.addOnDestinationChangedListener { _: NavController, navDestination: NavDestination, _: Bundle? ->
            binding.bottomNavigation.visibility =
                if (topLevelDestinationIds.contains(navDestination.id)) View.VISIBLE else View.GONE
        }
    }

    companion object {
        val topLevelDestinationIds = setOf(
            R.id.listFragment,
            R.id.favoriteFragment,
            R.id.settingsFragment,
        )
    }
}