package com.github.flatit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.flatit.databinding.ActivityMainBinding
import com.github.flatit.ui.billing.BillingFragment
import com.github.flatit.ui.overview.OverviewFragment
import com.github.flatit.ui.shoppinglist.ShoppingListFragment
import com.github.flatit.ui.todos.TodosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val selectedFragment = "selectedFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(savedInstanceState?.getInt(selectedFragment))

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            loadFragment(item.itemId)

            true
        }
    }

    private fun loadFragment(id: Int?) {
        supportFragmentManager.beginTransaction().replace(R.id.container, getFragment(id)).commit()
        supportActionBar?.title = getFragmentTitle(id)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        outState.putInt(selectedFragment, navigation.selectedItemId)
    }

    private fun getFragment(id: Int?) : Fragment {
        return when(id) {
            R.id.page_overview -> {
                OverviewFragment()
            }
            R.id.page_shopping_list -> {
                ShoppingListFragment()
            }
            R.id.page_billing -> {
                BillingFragment()
            }
            R.id.page_todos -> {
                TodosFragment()
            }
            else -> OverviewFragment()
        }
    }

    private fun getFragmentTitle(id: Int?) : String {
        return when(id) {
            R.id.page_overview -> {
                getString(R.string.overview)
            }
            R.id.page_shopping_list -> {
                getString(R.string.shopping)
            }
            R.id.page_billing -> {
                getString(R.string.billing)
            }
            R.id.page_todos -> {
                getString(R.string.todos)
            }
            else -> getString(R.string.overview)
        }

    }
}