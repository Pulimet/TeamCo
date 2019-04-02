package net.alexandroid.teamco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        navController = findNavController(R.id.myNavHostFragment)

        // Update action bar to reflect navigation
        NavigationUI.setupActionBarWithNavController(
                this,
                navController,
                drawerLayout)

        val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.loginFragment, R.id.homeFragment),
                drawerLayout)
        NavigationUI.setupWithNavController(toolBar, navController, appBarConfiguration)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, drawerLayout)

}
