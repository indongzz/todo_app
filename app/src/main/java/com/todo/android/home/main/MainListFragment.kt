package com.todo.android.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.todo.android.databinding.FragmentMainListBinding
import com.todo.android.home.TodoListAdapter

class MainListFragment : Fragment() {
    lateinit var binding: FragmentMainListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainListBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerMain
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //val adapter = TodoListAdapter(list)
        //recyclerView.adapter = adapter

        return binding.root
    }
}