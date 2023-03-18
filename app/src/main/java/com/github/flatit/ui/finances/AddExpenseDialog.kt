package com.github.flatit.ui.finances

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.github.flatit.R
import com.github.flatit.data.model.FinancesExpenseItem
import com.github.flatit.databinding.DialogFinancesAddBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Timestamp
import java.util.*

class AddExpenseDialog(
    private val financeOperationFunc: (item: FinancesExpenseItem) -> Unit,
    private val financeExpenseItem: FinancesExpenseItem?
) : DialogFragment() {

    private lateinit var binding: DialogFinancesAddBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFinancesAddBinding.inflate(requireActivity().layoutInflater)

        binding.expensesInputTitle.setText(financeExpenseItem?.title)
        binding.expensesInputDescription.setText(financeExpenseItem?.description)
        binding.expensesInputCosts.setText(financeExpenseItem?.expense?.toString() ?: "")
        binding.expensesDropDownPerson.setText(financeExpenseItem?.person)

        val dialog = MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.add_expense)
            .setView(binding.root)
            .setPositiveButton(R.string.save) { _, _ ->
                financeOperationFunc(
                    FinancesExpenseItem(
                        id = financeExpenseItem?.id ?: UUID.randomUUID().toString(),
                        title = binding.expensesInputTitle.text.toString(),
                        description = binding.expensesInputDescription.text.toString(),
                        expense = binding.expensesInputCosts.text.toString().toDoubleOrNull() ?: 0.0,
                        person = binding.expensesDropDownPerson.text.toString(),
                        timestamp = financeExpenseItem?.timestamp ?: Timestamp.now()
                    )
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

        // check if title, expenses and dropdownperson field is not empty, if all are filled 'add' or 'edit' button can be pressed
        var nonNullTextInput = Triple(binding.expensesInputTitle, binding.expensesInputCosts, binding.expensesDropDownPerson)
        nonNullTextInput.toList().forEach {
            it.addTextChangedListener {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = binding.expensesInputTitle.text?.isNotEmpty() ?: false && binding.expensesInputCosts.text?.isNotEmpty() ?: false && binding.expensesDropDownPerson.text?.isNotEmpty() ?: false
            }
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.expensesInputTitle.requestFocus()
        requireDialog().window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        // list of available flatmates
        val flatMates = listOf<String>("Moritz", "Johannes", "Justin")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, flatMates)
        binding.expensesDropDownPerson.setAdapter(arrayAdapter)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}