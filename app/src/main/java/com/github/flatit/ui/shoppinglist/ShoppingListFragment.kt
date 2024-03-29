package com.github.flatit.ui.shoppinglist

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.R
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.databinding.FragmentShoppingListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment() {

    private val shoppingListViewModel by viewModel<ShoppingListViewModel>()
    private val shoppingListRepository by inject<ShoppingListRepository>()
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(context)
        adapter = ShoppingListAdapter(
            onItemChecked = ::onItemChecked,
            onIncrement = ::onIncrement,
            onDecrement = ::onDecrement
        )
        binding.shoppingList.adapter = adapter
        binding.shoppingList.layoutManager = layoutManager

        shoppingListViewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.floatingActionButton.setOnClickListener {
            AddItemDialog().show(parentFragmentManager, "AddItemDialogNew")
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_shopping_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete_shopping_list -> {
                        shoppingListRepository.deleteSelectedItems()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun onItemChecked(item: ShoppingListItem) {
        shoppingListRepository.updateItem(item)
    }

    private fun onIncrement(item: ShoppingListItem) {
        shoppingListRepository.updateItem(item)
    }

    private fun onDecrement(item: ShoppingListItem) {
        shoppingListRepository.updateItem(item)
    }
}