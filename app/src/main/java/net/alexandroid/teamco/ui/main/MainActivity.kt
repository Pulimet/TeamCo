package net.alexandroid.teamco.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import net.alexandroid.teamco.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolBarAndNavigation()
    }

    private fun setToolBarAndNavigation() {
        setSupportActionBar(toolBar)
        navController = findNavController(R.id.myNavHostFragment)

        // Update action bar to reflect navigation
        // The title in the action bar will automatically be updated when the destination changes
        setupActionBarWithNavController(this, navController, drawerLayout)

        // This will call #onNavDestinationSelected(...)} when a menu item is selected.
        setupWithNavController(navView, navController)

        // Set Login and Home fragments as parent fragments
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment, R.id.homeFragment),
            drawerLayout
        )

        setupWithNavController(toolBar, navController, appBarConfiguration)

        //Hide hamburger icon at LoginFragment
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        //Lock drawer everywhere except HomeFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, drawerLayout)

}
