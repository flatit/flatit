package com.github.flatit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.flatit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val selectedFragment = "selectedFragment"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.overviewFragment, R.id.shoppingListFragment, R.id.financesFragment, R.id.todosFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        outState.putInt(selectedFragment, navigation.selectedItemId)
    }
}