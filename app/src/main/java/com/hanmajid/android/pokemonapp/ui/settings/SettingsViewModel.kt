package com.hanmajid.android.pokemonapp.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.hanmajid.android.pokemonapp.di.DatabaseSpModule.Companion.SP_KEY_ENABLE_CRASH_REPORTING
import org.koin.android.annotation.KoinViewModel

/**
 * [SettingsFragment] ViewModel class.
 */
@KoinViewModel
class SettingsViewModel(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    /**
     * Refresh counter.
     */
    private val refreshCount = MutableLiveData(0)

    /**
     * Whether crash reporting is enabled or not.
     */
    val enableCrashReporting = Transformations.map(refreshCount) {
        sharedPreferences.getBoolean(SP_KEY_ENABLE_CRASH_REPORTING, false)
    }

    /**
     * Update the value of [enableCrashReporting] with [checked].
     */
    fun updateEnableCrashReporting(checked: Boolean) {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(checked)
        with(sharedPreferences.edit()) {
            putBoolean(SP_KEY_ENABLE_CRASH_REPORTING, checked)
            commit()
        }
        refreshCount.value?.let {
            refreshCount.value = it + 1
        }
    }
}
