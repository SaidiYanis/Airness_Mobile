package com.airness.myapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.airness.myapplication.R
import com.airness.myapplication.databinding.ActivityMainBinding
import com.airness.myapplication.viewmodel.CartViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_detail -> supportActionBar?.title = ""
                else -> supportActionBar?.title = ""
            }
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
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

        binding.toolbar.findViewById<ImageView>(R.id.toolbar_logo).setOnClickListener {
            navController.navigate(R.id.nav_home, null, getNavOptions())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.nav_cart)
                true
            }
            else -> super.onOptionsItemSelected(item)
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
