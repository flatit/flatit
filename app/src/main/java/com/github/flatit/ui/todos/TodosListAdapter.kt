package com.github.flatit.ui.todos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.data.model.TodosListItem
import com.github.flatit.databinding.ItemTodosBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TodosListAdapter(
    private val onItemChecked: (item: TodosListItem) -> Unit
) : ListAdapter<TodosListItem, TodosListAdapter.TodosViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return TodosViewHolder(ItemTodosBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            checkboxTodos.isChecked = item.checked
            checkboxTodos.setOnCheckedChangeListener { _, isChecked ->
                onItemChecked(
                    TodosListItem(id = item.id, title = item.title, description = item.description, checked = isChecked)
                )
            }

            textViewTodosText.text = item.title
            textViewTodosDescription.text = item.description

            cardTodos.setOnClickListener {
                MaterialAlertDialogBuilder(root.context)
                    .setTitle(item.title)
                    .setMessage(item.description)
                    .show()
            }
        }
    }

    class TodosViewHolder(
        val binding: ItemTodosBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<TodosListItem>() {
        override fun areItemsTheSame(oldItem: TodosListItem, newItem: TodosListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodosListItem, newItem: TodosListItem): Boolean {
            return oldItem == newItem
        }
    }


}