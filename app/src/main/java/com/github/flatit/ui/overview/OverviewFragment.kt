package com.github.flatit.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.R
import com.github.flatit.databinding.FragmentOverviewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverviewFragment : Fragment() {

    private val overviewViewModel by viewModel<OverviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val navController = findNavController()

        // Shopping list
        binding.overviewCardShopping.setOnClickListener { navController.navigate(R.id.shoppingListFragment) }
        binding.overviewCardShoppingChevron.setOnClickListener { navController.navigate(R.id.shoppingListFragment) }

        val shoppingListAdapter = OverviewShoppingListAdapter(navController)
        binding.overviewShoppingList.layoutManager = LinearLayoutManager(context)
        binding.overviewShoppingList.adapter = shoppingListAdapter

        overviewViewModel.shoppingListItems.observe(viewLifecycleOwner) {
            shoppingListAdapter.submitList(it)
        }

        // Finances
        binding.overviewCardFinances.setOnClickListener { navController.navigate(R.id.financesFragment) }
        binding.overviewCardFinancesChevron.setOnClickListener { navController.navigate(R.id.financesFragment) }

        val financesAdapter = OverviewFinancesAdapter(navController)
        binding.overviewDebts.layoutManager = LinearLayoutManager(context)
        binding.overviewDebts.adapter = financesAdapter

        overviewViewModel.debtItems.observe(viewLifecycleOwner) {
            financesAdapter.submitList(it)
        }

        // Todos
        binding.overviewCardTodos.setOnClickListener { navController.navigate(R.id.todosFragment) }
        binding.overviewCardTodosChevron.setOnClickListener { navController.navigate(R.id.todosFragment) }

        val todosAdapter = OverviewTodosAdapter(navController)
        binding.overviewTodos.layoutManager = LinearLayoutManager(context)
        binding.overviewTodos.adapter = todosAdapter

        overviewViewModel.todosItems.observe(viewLifecycleOwner) {
            todosAdapter.submitList(it)
        }

        return binding.root
    }

}