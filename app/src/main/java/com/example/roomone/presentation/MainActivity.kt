package com.example.roomone.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.roomone.R
import com.example.roomone.databinding.ActivityMainBinding
import com.example.roomone.presentation.tabs.TabCategories
import com.example.roomone.presentation.tabs.TabFilters
import com.example.roomone.presentation.tabs.TabPanel
import com.example.roomone.presentation.tabs.TabProducts
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        supportFragmentManager.beginTransaction().replace(R.id.content, TabPanel()).commit()

        binding?.bottomNav?.setOnNavigationItemSelectedListener(this)
        binding?.bottomNav?.selectedItemId = R.id.panelItemBottomNav

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.panelItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabPanel()).commit()
            R.id.catalogProductsItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabProducts()).commit()
            R.id.catalogClothesItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, TabFilters()).commit()
            R.id.catalogCategoriesItemBottomNav -> supportFragmentManager.beginTransaction().replace(
                R.id.content, TabCategories()).commit()
        }

        return true
    }
}