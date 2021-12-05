package com.coppel.superhero

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coppel.superhero.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
        var currentFrag = navHostFragment?.childFragmentManager?.fragments?.get(0)
        if (currentFrag !is MainFragment) {
            super.onBackPressed()
        }
    }
}