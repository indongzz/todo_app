package com.todo.android.home.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.todo.android.TodoApplication
import com.todo.android.TodoRepository
import com.todo.android.databinding.FragmentCompleteListBinding
import com.todo.android.databinding.FragmentMainListBinding
import com.todo.android.home.TodoListAdapter
import com.todo.android.home.dialog.HomeBottomSheetFragment
import com.todo.android.home.main.MainViewModel

class CompleteListFragment : Fragment() {
    var _binding: FragmentCompleteListBinding? = null
    val binding: FragmentCompleteListBinding get() = _binding!!

    private val viewModel: CompleteListViewModel by viewModels {
        CompleteListViewModel.Factory((requireActivity().application as TodoApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompleteListBinding.inflate(inflater, container, false)
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
        val recyclerView = binding.recyclerComplete
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

