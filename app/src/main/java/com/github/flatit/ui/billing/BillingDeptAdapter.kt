package com.github.flatit.ui.billing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.BillingDeptItem
import com.github.flatit.data.model.BillingExpenseItem
import com.github.flatit.databinding.SingleDeptBinding
import com.github.flatit.databinding.SingleExpenseBinding

class BillingDeptAdapter : ListAdapter<BillingDeptItem, BillingDeptAdapter.BillingViewHolder>(Diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BillingViewHolder(SingleDeptBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BillingViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            var dept: String = ""
            if (item.dept >= 0) dept = " gets " else dept = " owes "
            singleDeptText.text = item.flatMate + dept + item.dept + "€"
        }
    }

    class BillingViewHolder(
        val binding: SingleDeptBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<BillingDeptItem>() {
        override fun areItemsTheSame(oldItem: BillingDeptItem, newItem: BillingDeptItem): Boolean {
            return oldItem.flatMate == newItem.flatMate && oldItem.dept == newItem.dept
        }

        override fun areContentsTheSame(oldItem: BillingDeptItem, newItem: BillingDeptItem): Boolean {
            return oldItem == newItem
        }
    }
}