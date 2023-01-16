package com.github.flatit.ui.finances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.data.model.FinancesExpenseItem
import com.github.flatit.data.model.Flatmate
import com.github.flatit.databinding.ItemDebtBinding

class FinancesDebtAdapter : ListAdapter<FinancesDebtItem, FinancesDebtAdapter.FinancesViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return FinancesViewHolder(ItemDebtBinding.inflate(inflater, parent, false))
    }


    override fun onBindViewHolder(holder: FinancesViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            var debt: String = ""
            if (item.debt >= 0) debt = " gets " else debt = " owes "
            singleDebtText.text = "${item.flatMate} ${debt} ${item.debt}€"
        }
    }

    class FinancesViewHolder(
        val binding: ItemDebtBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<FinancesDebtItem>() {
        override fun areItemsTheSame(oldItem: FinancesDebtItem, newItem: FinancesDebtItem): Boolean {
            return oldItem.flatMate == newItem.flatMate
        }

        override fun areContentsTheSame(
            oldItem: FinancesDebtItem,
            newItem: FinancesDebtItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}