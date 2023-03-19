package com.github.flatit.ui.finances

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.R
import com.github.flatit.data.model.FinancesExpenseItem
import com.github.flatit.databinding.ItemExpenseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FinancesExpenseAdapter (
    private val onExpenseEdit: (item: FinancesExpenseItem) -> Unit,
    private val onExpenseDelete: (item: FinancesExpenseItem) -> Unit,
    private val parentFragmentManager: FragmentManager
) : ListAdapter<FinancesExpenseItem, FinancesExpenseAdapter.FinancesViewHolder>(Diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return FinancesViewHolder(ItemExpenseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FinancesViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            expenseTextViewText.text = SpannableStringBuilder().bold { append(item.title)}
            expenseTextViewDescription.text = item.description

            cardExpense.setOnClickListener {
                MaterialAlertDialogBuilder(root.context)
                    .setTitle(item.title)
                    .setMessage(
                        "Description: " + item.description + "\n\n" +
                        item.person + " add expense of " + item.expense +
                        "€ on " + item.timestamp.toDate().toLocaleString()
                    ).setNeutralButton(R.string.delete) { _, _ ->
                        onExpenseDelete(item)
                    }.setPositiveButton(R.string.edit) { _, _ ->
                        SaveExpenseDialog(onExpenseEdit, item).show(parentFragmentManager, "DialogEditExpense")
                    }.show()
            }
        }
    }

    class FinancesViewHolder(
        val binding: ItemExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<FinancesExpenseItem>() {
        override fun areItemsTheSame(oldItem: FinancesExpenseItem, newItem: FinancesExpenseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FinancesExpenseItem, newItem: FinancesExpenseItem): Boolean {
            return oldItem == newItem
        }
    }
}