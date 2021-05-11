package com.todo.android.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.todo.android.R
import com.todo.android.databinding.ActivityMainListBinding

class MainListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainListBinding
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set toolbar to appbar in this activity.
        val toolbar = binding.appBarMainList.mainToolbar
        setSupportActionBar(toolbar)

        val navController =
            findNavController(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)!!)
        val drawLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.main_list, R.id.complete_list),
            drawLayout
        )
        val collapsingToolbarLayout = binding.appBarMainList.collapsingTollbarMain
        //setupActionBarWithNavController(navController, appBarConfiguration)
        collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
        binding.navMain.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)!!)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_add -> {
                // add icon
                true
            }
            R.id.toolbar_search -> {
                // search icon
                true
            }
            R.id.toolbar_sort -> {
                // sort icon
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
