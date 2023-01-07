package com.github.flatit.ui.todos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.TodosListItem
import com.github.flatit.databinding.ItemTodosBinding

class TodosListAdapter : ListAdapter<TodosListItem, TodosListAdapter.TodosViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return TodosViewHolder(ItemTodosBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            checkboxTodos.isChecked = item.checked
            textViewTodosText.text = item.title
            textViewTodosDescription.text = item.description
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