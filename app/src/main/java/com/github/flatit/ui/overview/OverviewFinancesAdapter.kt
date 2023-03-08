package com.github.flatit.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.databinding.ItemOverviewDebtBinding

class OverviewFinancesAdapter :
    ListAdapter<FinancesDebtItem, OverviewFinancesAdapter.OverviewFinancesViewHolder>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverviewFinancesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return OverviewFinancesViewHolder(
            ItemOverviewDebtBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OverviewFinancesViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            var debt: String = ""
            if (item.debt >= 0) debt = " owes " else debt = " gets "
            itemOverviewFinancesDebt.text = "${item.flatMate} ${debt} ${item.debt}€"
        }
    }

    class OverviewFinancesViewHolder(
        val binding: ItemOverviewDebtBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<FinancesDebtItem>() {
        override fun areItemsTheSame(
            oldItem: FinancesDebtItem,
            newItem: FinancesDebtItem
        ): Boolean {
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