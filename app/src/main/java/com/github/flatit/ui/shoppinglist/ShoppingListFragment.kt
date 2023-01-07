package com.github.flatit.ui.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.R
import com.github.flatit.databinding.FragmentShoppingListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListFragment : Fragment() {

    private val shoppingListViewModel by viewModel<ShoppingListViewModel> ()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(context)
        val adapter = ShoppingListAdapter()
        binding.shoppingList.adapter = adapter
        binding.shoppingList.layoutManager = layoutManager

        shoppingListViewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        var fab = root.findViewById<FloatingActionButton>(R.id.floating_action_button)
        if (fab != null) {
            fab.setOnClickListener {
                val dialog = AddItemDialog()
                dialog.show(parentFragmentManager, "dialog")
            }
        }

        return root
    }
}