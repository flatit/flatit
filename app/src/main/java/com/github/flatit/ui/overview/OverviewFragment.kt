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

        binding.overviewCardShopping.setOnClickListener { selectFragment(R.id.page_shopping_list) }
        binding.overviewCardShoppingChevron.setOnClickListener { selectFragment(R.id.page_shopping_list) }

        binding.overviewCardFinances.setOnClickListener { selectFragment(R.id.page_finances) }
        binding.overviewCardFinancesChevron.setOnClickListener { selectFragment(R.id.page_finances) }

        binding.overviewCardTodos.setOnClickListener { selectFragment(R.id.page_todos) }
        binding.overviewCardTodosChevron.setOnClickListener { selectFragment(R.id.page_todos) }

        val shoppingListAdapter = OverviewShoppingListAdapter()
        binding.overviewShoppingList.layoutManager = LinearLayoutManager(context)
        binding.overviewShoppingList.adapter = shoppingListAdapter

        overviewViewModel.shoppingListItems.observe(viewLifecycleOwner) {
            shoppingListAdapter.submitList(it)
        }

        val financesAdapter = OverviewFinancesAdapter()
        binding.overviewDebts.layoutManager = LinearLayoutManager(context)
        binding.overviewDebts.adapter = financesAdapter

        overviewViewModel.debtItems.observe(viewLifecycleOwner) {
            financesAdapter.submitList(it)
        }

        val todosAdapter = OverviewTodosAdapter()
        binding.overviewTodos.layoutManager = LinearLayoutManager(context)
        binding.overviewTodos.adapter = todosAdapter

        overviewViewModel.todosItems.observe(viewLifecycleOwner) {
            todosAdapter.submitList(it)
        }

        return binding.root
    }

}