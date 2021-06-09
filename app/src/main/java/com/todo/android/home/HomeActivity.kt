package com.todo.android.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.todo.android.R
import com.todo.android.databinding.ActivityHomeBinding
import com.todo.android.home.dialog.HomeBottomSheetFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set toolbar to appbar in this activity.
        val toolbar = binding.appBarHome.toolbarHome
        setSupportActionBar(toolbar)

        val navController =
            findNavController(
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home) as NavHostFragment)
        val drawLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.main_list, R.id.complete_list),
            drawLayout
        )
        val collapsingToolbarLayout = binding.appBarHome.collapsingTollbarHome
        //setupActionBarWithNavController(navController, appBarConfiguration)
        collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
        binding.navMain.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home)!!)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_list, menu)
        val searchItem = menu?.findItem(R.id.toolbar_search)
        val addItem = menu?.findItem(R.id.toolbar_add)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_add -> {
                val bottomSheetDialog = HomeBottomSheetFragment()
                bottomSheetDialog.show(supportFragmentManager, "addBottomSheet")
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
