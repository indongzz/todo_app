package com.todo.android.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.todo.android.databinding.FragmentCompleteListBinding

class CompleteListFragment : Fragment() {
    lateinit var binding: FragmentCompleteListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompleteListBinding.inflate(inflater, container, false)
        return binding.root
    }
}