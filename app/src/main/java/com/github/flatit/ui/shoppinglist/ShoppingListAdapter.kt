package com.github.flatit.ui.shoppinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.databinding.ItemShoppingListBinding

class ShoppingListAdapter() : ListAdapter<ShoppingListItem, ShoppingListAdapter.ShoppingListViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ShoppingListViewHolder(ItemShoppingListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            checkBoxTitle.text = item.text
            checkBoxTitle.isChecked = item.checked
            textviewAmount.text = item.amount.toString()

        }
    }

    class ShoppingListViewHolder(
        val binding: ItemShoppingListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<ShoppingListItem>() {
        override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
            return oldItem == newItem
        }
    }
}