package com.github.flatit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.flatit.ui.OverviewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, OverviewFragment()).commit()
    }
}