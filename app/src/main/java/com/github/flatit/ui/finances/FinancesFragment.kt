package com.github.flatit.ui.finances

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.data.FinancesRepository
import com.github.flatit.data.FlatmateRepository
import com.github.flatit.databinding.FragmentFinancesBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinancesFragment : Fragment() {

    private val financesViewModel by viewModel<FinancesViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFinancesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val expenseLayoutManager = LinearLayoutManager(context)
        val deptLayoutManager = LinearLayoutManager(context)
        val expenseAdapter = FinancesExpenseAdapter()
        val deptAdapter = FinancesDebtAdapter()

        binding.debts.adapter = deptAdapter
        binding.expenses.adapter = expenseAdapter
        binding.debts.layoutManager = deptLayoutManager
        binding.expenses.layoutManager = expenseLayoutManager

        financesViewModel.items.observe(viewLifecycleOwner) {
            expenseAdapter.submitList(it)
        }
        financesViewModel.debts.observe(viewLifecycleOwner) {
            deptAdapter.submitList(it)
        }

        return root
    }
}