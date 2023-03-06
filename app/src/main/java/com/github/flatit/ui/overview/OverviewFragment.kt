package com.github.flatit.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.flatit.R
import com.github.flatit.databinding.FragmentOverviewBinding

class OverviewFragment(
    private val selectFragment: (id: Int?) -> Unit
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater, container, false)

        binding.overviewCardShopping.setOnClickListener { selectFragment(R.id.page_shopping_list) }
        binding.overviewCardFinances.setOnClickListener { selectFragment(R.id.page_finances) }
        binding.overviewCardTodos.setOnClickListener { selectFragment(R.id.page_todos) }

        return binding.root
    }

}