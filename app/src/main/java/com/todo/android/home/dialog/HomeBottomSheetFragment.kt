package com.todo.android.home.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.todo.android.database.Item
import com.todo.android.databinding.HomeBottomSheetBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

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
        savedInstanceState: Bundle?,
    ): View? {
        _binding = HomeBottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.event.observe(viewLifecycleOwner, { event ->
            when (event) {
                HomeBottomSheetViewModel.TIME_CHIP_CLICKED -> showDatePickerDialog()
                HomeBottomSheetViewModel.DATE_CHIP_CLICKED -> showTimePickerDialog()
                HomeBottomSheetViewModel.SUBMIT_CLICKED -> dismissAllowingStateLoss()
            }
        })

        arguments?.apply {
            getSerializable("item")?.run {
                this as Item.ContentEntity
                viewModel.title.value = title
                viewModel.chipDate.value = baseDate
                viewModel.chipTime.value = time
            }
        }

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        _binding = null
    }

    private fun showTimePickerDialog() {
        val time = viewModel.chipTime.value ?: LocalTime.now()
        TimePickerDialog(requireContext(), { _, hour, min ->
            LocalTime.of(hour, min).also { viewModel.chipTime.value = it }
        }, time.hour, time.minute, false).show()
    }


    private fun showDatePickerDialog() {
        val date = viewModel.chipDate.value ?: LocalDate.now()
        DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            LocalDate.of(year, month + 1, dayOfMonth).also { viewModel.chipDate.value = it }
        }, date.year, date.monthValue - 1, date.dayOfMonth).show()

    }
}