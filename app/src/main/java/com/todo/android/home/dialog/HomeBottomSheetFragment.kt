package com.todo.android.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.todo.android.databinding.HomeBottomSheetBinding

class HomeBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: HomeBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}