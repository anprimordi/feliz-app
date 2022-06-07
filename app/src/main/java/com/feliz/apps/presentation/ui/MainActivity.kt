package com.feliz.apps.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: MaterialToolbar
    private lateinit var appBar: AppBarLayout
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bottomNavView = binding.navView
        toolbar = binding.toolbar
        appBar = binding.appbar

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.vendorFragment,
                R.id.formFragment
            )
        )

        toolbar.setupWithNavController(navController, appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        toolbar.isVisible = false
        bottomNavView.isVisible = false

        when (destination.id) {
            R.id.homeFragment,
            R.id.vendorFragment,
            R.id.formFragment -> {
                toolbar.isVisible = false
                bottomNavView.isVisible = true
            }
            else -> {
                toolbar.isVisible = false
                bottomNavView.isVisible = false
            }
        }
    }
}