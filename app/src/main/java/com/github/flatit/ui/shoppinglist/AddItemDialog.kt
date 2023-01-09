package com.github.flatit.ui.shoppinglist

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.github.flatit.R
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.model.ShoppingListItem
import com.github.flatit.databinding.DialogShoppingListAddBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject
import java.util.*

class AddItemDialog : DialogFragment() {

    private lateinit var binding: DialogShoppingListAddBinding
    private val shoppingListRepository by inject<ShoppingListRepository> ()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogShoppingListAddBinding.inflate(requireActivity().layoutInflater)

        val dialog = MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.add_item)
            .setView(binding.root)
            .setPositiveButton(R.string.add) { _, _ ->
                shoppingListRepository.addItem(
                    ShoppingListItem(
                        id = UUID.randomUUID().toString(),
                        text = binding.shoppingListInputTitle.text.toString(),
                        checked = false,
                        amount = 1)
                )

                dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                dismiss()
            }
            .create()

        // disable add button on dialog open
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = false
        }

        binding.shoppingListInputTitle.addTextChangedListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = binding.shoppingListInputTitle.text?.isNotEmpty() ?: false
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.shoppingListInputTitle.requestFocus()
        requireDialog().window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}