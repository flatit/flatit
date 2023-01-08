package com.github.flatit.ui.billing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.BillingDebtItem
import com.github.flatit.databinding.SingleDebtBinding

class BillingDebtAdapter :
    ListAdapter<BillingDebtItem, BillingDebtAdapter.BillingViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BillingViewHolder(SingleDebtBinding.inflate(inflater, parent, false))
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
        val binding: SingleDebtBinding
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