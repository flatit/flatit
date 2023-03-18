package com.github.flatit.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.R
import com.github.flatit.databinding.FragmentOverviewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverviewFragment(
    private val selectFragment: (id: Int?) -> Unit
) : Fragment() {

    private val overviewViewModel by viewModel<OverviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater, container, false)

        // Shopping list
        binding.overviewCardShopping.setOnClickListener { selectFragment(R.id.page_shopping_list) }
        binding.overviewCardShoppingChevron.setOnClickListener { selectFragment(R.id.page_shopping_list) }

        val shoppingListAdapter = OverviewShoppingListAdapter(selectFragment)
        binding.overviewShoppingList.layoutManager = LinearLayoutManager(context)
        binding.overviewShoppingList.adapter = shoppingListAdapter

        overviewViewModel.shoppingListItems.observe(viewLifecycleOwner) {
            shoppingListAdapter.submitList(it)
        }

        // Finances
        binding.overviewCardFinances.setOnClickListener { selectFragment(R.id.page_finances) }
        binding.overviewCardFinancesChevron.setOnClickListener { selectFragment(R.id.page_finances) }

        val financesAdapter = OverviewFinancesAdapter(selectFragment)
        binding.overviewDebts.layoutManager = LinearLayoutManager(context)
        binding.overviewDebts.adapter = financesAdapter

        overviewViewModel.debtItems.observe(viewLifecycleOwner) {
            financesAdapter.submitList(it)
        }

        // Todos
        binding.overviewCardTodos.setOnClickListener { selectFragment(R.id.page_todos) }
        binding.overviewCardTodosChevron.setOnClickListener { selectFragment(R.id.page_todos) }

        val todosAdapter = OverviewTodosAdapter(selectFragment)
        binding.overviewTodos.layoutManager = LinearLayoutManager(context)
        binding.overviewTodos.adapter = todosAdapter

        overviewViewModel.todosItems.observe(viewLifecycleOwner) {
            todosAdapter.submitList(it)
        }

        return binding.root
    }

}