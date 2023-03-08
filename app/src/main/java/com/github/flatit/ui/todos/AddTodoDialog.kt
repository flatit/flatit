package com.github.flatit.ui.todos

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
import com.github.flatit.data.TodosRepository
import com.github.flatit.data.model.TodosListItem
import com.github.flatit.databinding.DialogTodosAddBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Timestamp
import org.koin.android.ext.android.inject
import java.util.*

class AddTodoDialog : DialogFragment() {

    private val todosRepository by inject<TodosRepository>()

    private lateinit var binding: DialogTodosAddBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogTodosAddBinding.inflate(requireActivity().layoutInflater)

        val dialog = MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.add_item)
            .setView(binding.root)
            .setPositiveButton(R.string.add) { _, _ ->
                todosRepository.addItem(
                    TodosListItem(
                        id = UUID.randomUUID().toString(),
                        title = binding.todosInputTitle.text.toString(),
                        description = binding.todosInputDescription.text.toString(),
                        checked = false,
                        createdAt = Timestamp.now()
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

        binding.todosInputTitle.addTextChangedListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = binding.todosInputTitle.text?.isNotEmpty() ?: false
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.todosInputTitle.requestFocus()
        requireDialog().window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}