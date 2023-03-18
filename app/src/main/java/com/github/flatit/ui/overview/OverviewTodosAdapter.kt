package com.github.flatit.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.R
import com.github.flatit.data.model.TodosListItem
import com.github.flatit.databinding.ItemOverviewTodoBinding

class OverviewTodosAdapter(
    private val selectFragment: (id: Int?) -> Unit
) : ListAdapter<TodosListItem, OverviewTodosAdapter.OverviewTodosViewHolder>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverviewTodosViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return OverviewTodosViewHolder(
            ItemOverviewTodoBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OverviewTodosViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.setOnClickListener { selectFragment(R.id.page_todos) }

        with(holder.binding) {
            itemOverviewTodosTitle.text = item.title
        }
    }

    class OverviewTodosViewHolder(
        val binding: ItemOverviewTodoBinding
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