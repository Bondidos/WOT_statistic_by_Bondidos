package com.bondidos.wotstatisticbybondidos.presentation.ui.login

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.THEME_PREFERENCE
import com.bondidos.wotstatisticbybondidos.presentation.ui.login.dialog_fragment.ExitFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _navController: NavController? = null
    private val navController: NavController get() = requireNotNull(_navController)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyTheme()
        _navController = findNavController(R.id.nav_host_fragment_container)
        setupActionBarWithNavController(navController)
    }

    private fun applyTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val themePref = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
                THEME_PREFERENCE, false
            )
            when (themePref) {
                true -> setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.navigate(R.id.settingsFragment)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigate(R.id.loginFragment)
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val dialog = ExitFragment()
        dialog.show(supportFragmentManager, "Exit?")
    }
}