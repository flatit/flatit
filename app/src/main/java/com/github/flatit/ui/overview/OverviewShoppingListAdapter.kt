package com.github.flatit.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.flatit.R
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.databinding.ItemOverviewShoppingListBinding

class OverviewShoppingListAdapter(
    private val navController: NavController
) : ListAdapter<ShoppingListItem, OverviewShoppingListAdapter.OverviewShoppingListViewHolder>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverviewShoppingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return OverviewShoppingListViewHolder(
            ItemOverviewShoppingListBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OverviewShoppingListViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.setOnClickListener { navController.navigate(R.id.shoppingListFragment) }

        with(holder.binding) {
            itemOverviewShoppingListTitle.text = itemOverviewShoppingListTitle.context.getString(R.string.overview_shopping_list_item, item.amount, item.text)
        }
    }

    class OverviewShoppingListViewHolder(
        val binding: ItemOverviewShoppingListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<ShoppingListItem>() {
        override fun areItemsTheSame(
            oldItem: ShoppingListItem,
            newItem: ShoppingListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShoppingListItem,
            newItem: ShoppingListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
