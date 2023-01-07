package com.github.flatit.ui.shoppinglist

import android.os.Bundle
import android.view.*
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.R
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.databinding.FragmentShoppingListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject

class ShoppingListFragment : Fragment() {

    private val shoppingListViewModel by viewModel<ShoppingListViewModel> ()
    private val shoppingListRepository by inject<ShoppingListRepository> ()
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
            onPositveButtonClicked = ::onPositveButtonClicked,
            onNegativeButtonClicked = ::onNegativeButtonClicked
        )
        binding.shoppingList.adapter = adapter
        binding.shoppingList.layoutManager = layoutManager

        shoppingListViewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

                root.findViewById<FloatingActionButton>(R.id.floating_action_button)?.setOnClickListener {
                    val dialog = AddItemDialog()
                    dialog.show(parentFragmentManager, "dialog")
        }


        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.delete_shopping_menu, menu)
       /* return super.onCreateOptionsMenu(menu, inflater)*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {/*
        if(adapter.itemCount > 1){
            for (item in 1 ..adapter.itemCount){
                if()
            }*/
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onItemChecked(item: ShoppingListItem) {
                shoppingListRepository.updateItem(item)
            }

    private fun onPositveButtonClicked(item: ShoppingListItem){
        shoppingListRepository.updateItem(item)
    }

    private fun onNegativeButtonClicked(item: ShoppingListItem){
        shoppingListRepository.updateItem(item)
    }
}