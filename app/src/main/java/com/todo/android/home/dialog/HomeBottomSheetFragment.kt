package com.todo.android.home.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.todo.android.R
import com.todo.android.databinding.HomeBottomSheetBinding

class HomeBottomSheetFragment : BottomSheetDialogFragment() {
    var _binding: HomeBottomSheetBinding? = null
    val binding: HomeBottomSheetBinding get() = _binding!!
    private val viewModel: HomeBottomSheetViewModel by viewModels {
        HomeBottomSheetViewModel.Factory(requireActivity().applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        return binding.root
    }
}