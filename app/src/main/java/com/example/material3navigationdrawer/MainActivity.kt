package com.example.material3navigationdrawer

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.material3navigationdrawer.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var themeSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Find DrawerLayout and NavigationView
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)

        // Configure AppBarConfiguration with top-level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment), // Top-level destinations
            drawerLayout
        )

        // Link NavController with NavigationView
        NavigationUI.setupWithNavController(navigationView, navController)
        // Link NavController with ActionBar (optional)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.apply {
            navigationView.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                drawerLayout.close()
                when (menuItem.itemId) {
                    R.id.nav_menu_home ->{
                        searchBar.visibility = View.VISIBLE // Hide SearchBar in Second Fragment
                    }
                    R.id.nav_menu_explore -> {
                        navController.navigate(R.id.action_homeFragment_to_detailFragment)
                        searchBar.visibility = View.GONE // Hide SearchBar in Second Fragment

                    }else -> {
                      // Show CoordinatorLayout for other fragments
                      searchBar.visibility = View.VISIBLE
                    }
                }
                return@setNavigationItemSelectedListener true
            }

            // Listen for navigation changes
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.homeFragment) {
                    searchBar.visibility = View.VISIBLE
                }
            }

        }

        // theme setup
        // Load saved theme preference
        sharedPreferences = getSharedPreferences("ThemePref", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        // Find the switch and set its state
        val headerView = navigationView.getHeaderView(0) // Access header view
        val themeSwitch = headerView.findViewById<Switch>(R.id.theme_switch)

        themeSwitch.isChecked = isDarkMode

        // Handle switch toggle
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveThemePreference(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                saveThemePreference(false)
            }
        }

//        val data: ArrayList<Any> = fetchRowData()
//        // for search adapter
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//
//        // For List with Header
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = MyAdapter(data)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val data = fetchRowData()

        // for List with header
        recyclerView.layoutManager = LinearLayoutManager(this)

        // for Grid with header
//        val spanCount = 3
//        val gridLayoutManager = GridLayoutManager(this, spanCount)
//        val sizeLookup: SpanSizeLookup = object : SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                // Because header takes full width therefore return spanCount for it
//                return if (data[position] is String) spanCount else 1
//            }
//        }
//
//        // for better performance according to android docs
//        sizeLookup.isSpanGroupIndexCacheEnabled = true
//        sizeLookup.isSpanIndexCacheEnabled = true
//        gridLayoutManager.spanSizeLookup = sizeLookup
//        recyclerView.layoutManager = gridLayoutManager

        recyclerView.setAdapter(MyAdapter(data));
    }

    private fun fetchRowData(): ArrayList<Any> {
        val data = ArrayList<Any>()
        var h = 1
        var p = 1
        for (i in 0..29) {
            if (i % 5 == 0) {
                data.add("Header$h")
                h++
            } else {
                data.add(DataItem("Title$p"))
                p++
            }
        }
        return data
    }

    private fun saveThemePreference(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkMode", isDarkMode)
        editor.apply()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}