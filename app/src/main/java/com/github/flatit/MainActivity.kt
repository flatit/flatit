package com.github.flatit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.flatit.ui.billing.BillingFragment
import com.github.flatit.ui.overview.OverviewFragment
import com.github.flatit.ui.shoppinglist.ShoppingListFragment
import com.github.flatit.ui.todos.TodosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, getFragment(savedInstanceState?.getInt("selectedFragment"))).commit()

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener { item ->
            supportFragmentManager.beginTransaction().replace(R.id.container, getFragment(item.itemId)).commit()

            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        outState.putInt("selectedFragment", navigation.selectedItemId)
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
}