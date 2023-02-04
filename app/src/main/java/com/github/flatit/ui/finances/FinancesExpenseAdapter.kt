package com.github.flatit.ui.finances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.R
import com.github.flatit.data.model.FinancesExpenseItem
import com.github.flatit.databinding.ItemExpenseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FinancesExpenseAdapter (
    private val onExpenseSave: (item: FinancesExpenseItem) -> Unit,
    private val onExpenseDelete: (item: FinancesExpenseItem) -> Unit
) : ListAdapter<FinancesExpenseItem, FinancesExpenseAdapter.FinancesViewHolder>(Diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return FinancesViewHolder(ItemExpenseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FinancesViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            expenseTextViewText.text = item.title
            expenseTextViewDescription.text = item.description
            val multiItems = arrayOf("Item 1", "Item 2", "Item 3")
            val checkedItems = booleanArrayOf(true, false, false, false)

            cardExpense.setOnClickListener {
                MaterialAlertDialogBuilder(root.context)
                    .setTitle(item.title)
                    .setMessage(
                        "Description: " + item.description + "\n\n" +
                        item.person + " add expense of " + item.expense +
                        "€ on " + item.timestamp.toDate().toLocaleString()
                    )

                    .setNeutralButton(R.string.delete) { _, _ ->
                        onExpenseDelete(item)
                    }
                    .setPositiveButton(R.string.save) { _, _ ->
                        onExpenseSave(
                            FinancesExpenseItem(
                                id = item.id,
                                title = item.title,
                                description = item.description,
                                person = item.person,
                                expense = item.expense,
                                timestamp = item.timestamp
                            )
                        )
                    }
                    /*.setMultiChoiceItems(multiItems, checkedItems) { _, _, _ ->
                        onExpenseSave(
                            FinancesExpenseItem(
                                id = item.id,
                                title = item.title,
                                description = item.description,
                                person = item.person,
                                expense = item.expense,
                                timestamp = item.timestamp
                            )
                        )
                    }*/.show()
            }
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