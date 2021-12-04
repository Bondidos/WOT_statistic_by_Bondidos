package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.bondidos.wotstatisticbybondidos.R

const val THEME_PREFERENCE = "switch_to_dark"

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        preference?.let { preference ->
            if (preference.key == THEME_PREFERENCE &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
            ) {
                val pref =
                    PreferenceManager.getDefaultSharedPreferences(requireContext()).getBoolean(
                        THEME_PREFERENCE, false
                    )
                if (pref) {
                    setDefaultNightMode(MODE_NIGHT_YES)
                } else setDefaultNightMode(MODE_NIGHT_NO)
            }
        }
        return super.onPreferenceTreeClick(preference)
    }

}