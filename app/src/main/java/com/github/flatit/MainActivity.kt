package com.github.flatit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.flatit.ui.billing.BillingFragment
import com.github.flatit.ui.overview.OverviewFragment
import com.github.flatit.ui.shoppinglist.ShoppingListFragment
import com.github.flatit.ui.todos.TodosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, OverviewFragment()).commit()

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_overview -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, OverviewFragment()).commit()
                    true
                }
                R.id.page_shopping_list -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ShoppingListFragment()).commit()
                    true
                }
                R.id.page_billing -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, BillingFragment()).commit()
                    true
                }
                R.id.page_todos -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, TodosFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}