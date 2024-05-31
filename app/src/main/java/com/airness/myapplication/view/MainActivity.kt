package com.airness.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.airness.myapplication.R
import com.airness.myapplication.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_products,
                R.id.nav_categories,
                R.id.nav_settings
            ),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation view item clicks here.
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.nav_home, null, getNavOptions())
                }
                R.id.nav_products -> {
                    navController.navigate(R.id.nav_products, null, getNavOptions())
                }
                R.id.nav_categories -> {
                    navController.navigate(R.id.nav_categories, null, getNavOptions())
                }
                R.id.nav_settings -> {
                    navController.navigate(R.id.nav_settings, null, getNavOptions())
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun getNavOptions(): NavOptions {
        return NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .build()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
