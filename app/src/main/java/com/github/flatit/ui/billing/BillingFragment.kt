package com.github.flatit.ui.billing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.flatit.databinding.FragmentBillingBinding

class BillingFragment : Fragment() {

    private val billingExpenseViewModel by viewModels<BillingExpenseViewModel> ()
    private val billingDeptViewModel by viewModels<BillingDeptViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBillingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val expenseLayoutManager = LinearLayoutManager(context)
        val deptLayoutManager = LinearLayoutManager(context)
        val expenseAdapter = BillingExpenseAdapter()
        val deptAdapter = BillingDeptAdapter()

        binding.depts.adapter = deptAdapter
        binding.expenses.adapter = expenseAdapter
        binding.depts.layoutManager = deptLayoutManager
        binding.expenses.layoutManager = expenseLayoutManager

        billingExpenseViewModel.items.observe(viewLifecycleOwner) {
            expenseAdapter.submitList(it)
        }
        billingDeptViewModel.items.observe(viewLifecycleOwner) {
            deptAdapter.submitList(it)
        }

        return root
    }
}