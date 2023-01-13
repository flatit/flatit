package com.github.flatit.ui.finances

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.databinding.FragmentFinancesBinding

class FinancesFragment : Fragment() {

    private val billingExpenseViewModel by viewModels<FinancesExpenseViewModel> ()
    private val billingDebtViewModel by viewModels<FinancesDebtViewModel> ()

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

        billingExpenseViewModel.items.observe(viewLifecycleOwner) {
            expenseAdapter.submitList(it)
        }
        billingDebtViewModel.items.observe(viewLifecycleOwner) {
            deptAdapter.submitList(it)
        }

        return root
    }
}