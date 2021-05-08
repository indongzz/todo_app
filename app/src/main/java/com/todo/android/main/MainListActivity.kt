package com.todo.android.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.todo.android.R
import com.todo.android.databinding.ActivityMainListBinding

class MainListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set toolbar to appbar in this activity.
        setSupportActionBar(binding.mainListToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        binding.mainListToolbar.inflateMenu(R.menu.actionbar_main_list)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_list_toolbar_add -> {
                // add icon
                true
            }
            R.id.main_list_toolbar_search -> {
                // search icon
                true
            }
            R.id.main_list_toolbar_sort -> {
                // sort icon
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
