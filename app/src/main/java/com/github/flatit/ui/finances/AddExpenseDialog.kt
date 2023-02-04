package com.github.flatit.ui.finances

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.github.flatit.R
import com.github.flatit.data.FinancesRepository
import com.github.flatit.data.FlatmateRepository
import com.github.flatit.data.model.FinancesDebtItem
import com.github.flatit.data.model.FinancesExpenseItem
import com.github.flatit.data.model.Flatmate
import com.github.flatit.databinding.DialogFinancesAddBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Timestamp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class AddExpenseDialog : DialogFragment() {

    private val addExpenseDialogViewModel by viewModel<AddExpenseDialogViewModel> ()
    private val financesRepository by inject<FinancesRepository>()
    private val flatmateRepository by inject<FlatmateRepository>()

    private lateinit var binding: DialogFinancesAddBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFinancesAddBinding.inflate(requireActivity().layoutInflater)

        //val flatMates = listOf<String>("Moritz", "Johannes", "Justin")
        //val flatMates = getFlatMatesAsStrings()

        //Log.i("Testing", "Size: " + flatmateRepository.getFlatmates().get + "----------------------------------------")
        /*flatMates.forEach {
            Log.i("Testing", "FlatMate: " + it + "-------------------------------------")
        }*/
        /*flatmateRepository.addFlatmate(
            Flatmate(
            id = "dasjd",
            name = "hasdd"
        )
        )*/

        /*val autoCompleteTextView = binding.expensesDropDownPerson*/



        val dialog = MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.add_expense)
            .setView(binding.root)
            .setPositiveButton(R.string.add) { _, _ ->
                financesRepository.addExpenseItem(
                    FinancesExpenseItem( // TODO: Wenn Falscheingaben User darauf hinweisen, im Moment jeder Wert möglich
                        id = UUID.randomUUID().toString(),
                        title = binding.expensesInputTitle.text.toString(),
                        description = binding.expensesInputDescription.text.toString(),
                        expense = binding.expensesInputCosts.text.toString().toDoubleOrNull() ?: 0.0,
                        person = binding.expensesDropDownPerson.text.toString(),
                        timestamp = Timestamp.now()
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

        binding.expensesInputTitle.addTextChangedListener { // TODO: nur wenn Title, expense und person ausgewählt Save button enablen
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = binding.expensesInputTitle.text?.isNotEmpty() ?: false
        }

        return dialog
    }

    private fun getFlatMatesAsStrings(flatMates: List<Flatmate>): List<String> {
        val flatMatesNames = mutableListOf<String>()
        Log.i("Testing", "FlatMates size: " + flatmateRepository.getFlatmates().value?.size + ".-------------------------------------")
        flatMates.forEach {
            flatMatesNames.add(it.name)
        }
        return flatMatesNames.toList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.expensesInputTitle.requestFocus()
        requireDialog().window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        val flatMates = listOf<String>("Moritz", "Johannes", "Justin")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, flatMates)
        binding.expensesDropDownPerson.setAdapter(arrayAdapter)

        /*addExpenseDialogViewModel.items.observe(this) { flatMate ->
            binding.expensesDropDownPerson.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    getFlatMatesAsStrings(flatMate)
                )
            )
        }*/

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}