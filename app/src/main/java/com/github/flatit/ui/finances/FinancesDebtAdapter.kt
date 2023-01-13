package com.github.flatit.ui.finances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.BillingDebtItem
import com.github.flatit.databinding.ItemDebtBinding

class FinancesDebtAdapter :
    ListAdapter<BillingDebtItem, FinancesDebtAdapter.BillingViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BillingViewHolder(ItemDebtBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BillingViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            var debt: String = ""
            if (item.debt >= 0) debt = " gets " else debt = " owes "
            singleDebtText.text = item.flatMate + debt + item.debt + "€"
        }
    }

    class BillingViewHolder(
        val binding: ItemDebtBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<BillingDebtItem>() {
        override fun areItemsTheSame(oldItem: BillingDebtItem, newItem: BillingDebtItem): Boolean {
            return oldItem.flatMate == newItem.flatMate && oldItem.debt == newItem.debt
        }

        override fun areContentsTheSame(
            oldItem: BillingDebtItem,
            newItem: BillingDebtItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}