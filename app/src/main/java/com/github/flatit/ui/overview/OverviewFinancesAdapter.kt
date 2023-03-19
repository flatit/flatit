package com.github.flatit.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.R
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.databinding.ItemOverviewDebtBinding
import kotlin.math.abs

class OverviewFinancesAdapter(
    // private val selectFragment: (id: Int?) -> Unit
) : ListAdapter<FinancesDebtItem, OverviewFinancesAdapter.OverviewFinancesViewHolder>(Diff) {

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

        // holder.itemView.setOnClickListener { selectFragment(R.id.page_finances) }

        with(holder.binding) {
            if (item.debt >= 0) {
                itemOverviewFinancesDebt.text = itemOverviewFinancesDebt.context.getString(
                    R.string.finances_owes,
                    item.flatMate,
                    item.debt
                )
            } else {
                itemOverviewFinancesDebt.text = itemOverviewFinancesDebt.context.getString(
                    R.string.finances_gets,
                    item.flatMate,
                    abs(item.debt)
                )
            }
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