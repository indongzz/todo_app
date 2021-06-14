package com.todo.android.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.todo.android.TodoApplication
import com.todo.android.databinding.FragmentMainListBinding
import com.todo.android.home.TodoListAdapter

class MainListFragment : Fragment() {
    var _binding: FragmentMainListBinding? = null
    val binding: FragmentMainListBinding get() = _binding!!
    private val viewModel: MainListViewModel by viewModels {
        MainListViewModel.Factory((activity?.application as TodoApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val recyclerView = binding.recyclerMain
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = TodoListAdapter()
        recyclerView.adapter = adapter

        viewModel.resultList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}