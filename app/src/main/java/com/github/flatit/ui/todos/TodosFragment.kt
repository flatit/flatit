package com.github.flatit.ui.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.R
import com.github.flatit.data.TodosRepository
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.data.model.TodosListItem
import com.github.flatit.databinding.FragmentTodosBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TodosFragment : Fragment() {

    private val todosViewModel by viewModel<TodosViewModel>()
    private val todosRepository by inject<TodosRepository>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(context)
        val adapter = TodosListAdapter(
            onItemChecked = ::onItemChecked
        )
        binding.todosList.adapter = adapter
        binding.todosList.layoutManager = layoutManager

        todosViewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.fabTodos.setOnClickListener {
            DialogAddTodo().show(parentFragmentManager, "DialogAddTodo")
        }

        return root
    }

    private fun onItemChecked(item: TodosListItem) {
        todosRepository.updateItem(item)
    }
}