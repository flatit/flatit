package com.github.flatit.ui.finances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.FinancesExpenseItem
import com.github.flatit.databinding.ItemExpenseBinding

class FinancesExpenseAdapter : ListAdapter<FinancesExpenseItem, FinancesExpenseAdapter.FinancesViewHolder>(Diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return FinancesViewHolder(ItemExpenseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FinancesViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            singleExpenseText.text = item.title
        }
    }

    class FinancesViewHolder(
        val binding: ItemExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<FinancesExpenseItem>() {
        override fun areItemsTheSame(oldItem: FinancesExpenseItem, newItem: FinancesExpenseItem): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: FinancesExpenseItem, newItem: FinancesExpenseItem): Boolean {
            return oldItem == newItem
        }
    }
}