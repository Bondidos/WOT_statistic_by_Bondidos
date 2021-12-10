package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.ActivityStatisticBinding
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants
import com.bondidos.wotstatisticbybondidos.presentation.ui.login.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Statistic : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatisticBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyTheme()

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_statistic)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.userDataFragment, R.id.achievesFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun applyTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val themePref = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
                Constants.THEME_PREFERENCE, false
            )
            when (themePref) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.navigate(R.id.settingsFragment2)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigate(R.id.userDataFragment)
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
        finish()
    }
}
