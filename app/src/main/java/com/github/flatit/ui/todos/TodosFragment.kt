package com.github.flatit.ui.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.data.TodosRepository
import com.github.flatit.data.model.TodosListItem
import com.github.flatit.databinding.FragmentTodosBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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
            onItemChecked = todosRepository::updateItem,
            onItemDelete = todosRepository::deleteItem
        )
        binding.todosList.adapter = adapter
        binding.todosList.layoutManager = layoutManager

        todosViewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.fabTodos.setOnClickListener {
            AddTodoDialog().show(parentFragmentManager, "DialogAddTodo")
        }

        return root
    }
}