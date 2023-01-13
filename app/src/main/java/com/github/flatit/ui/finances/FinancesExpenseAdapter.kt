package com.github.flatit.ui.finances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.BillingExpenseItem
import com.github.flatit.databinding.ItemExpenseBinding

class FinancesExpenseAdapter : ListAdapter<BillingExpenseItem, FinancesExpenseAdapter.BillingViewHolder>(Diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BillingViewHolder(ItemExpenseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BillingViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            singleExpenseText.text = item.title
        }
    }

    class BillingViewHolder(
        val binding: ItemExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<BillingExpenseItem>() {
        override fun areItemsTheSame(oldItem: BillingExpenseItem, newItem: BillingExpenseItem): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: BillingExpenseItem, newItem: BillingExpenseItem): Boolean {
            return oldItem == newItem
        }
    }
}