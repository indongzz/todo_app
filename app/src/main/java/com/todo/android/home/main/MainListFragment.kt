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
import com.todo.android.home.dialog.HomeBottomSheetFragment

class MainListFragment : Fragment() {
    var _binding: FragmentMainListBinding? = null
    val binding: FragmentMainListBinding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory((requireActivity().application as TodoApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.onClickedItem.observe(viewLifecycleOwner, {
            val bottomSheetDialog = HomeBottomSheetFragment()
            val args = Bundle()
            args.putSerializable("item", it)
            bottomSheetDialog.arguments = args

            bottomSheetDialog.show(parentFragmentManager, "addBottomSheet")
        })

        // setup recyclerview
        val recyclerView = binding.recyclerMain
        val adapter = TodoListAdapter(viewModel, viewLifecycleOwner)
        val layoutManager = LinearLayoutManager(activity)
        //val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(TodoListAdapter.DividerItemDecoration(recyclerView.context))

        /*viewModel.combineList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }*/

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}