package com.github.flatit.ui.shoppinglist

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.github.flatit.R
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.model.ShoppingListItem
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.ext.android.inject
import java.util.*

class AddItemDialog() : DialogFragment(){

    private val shoppingListRepository by inject<ShoppingListRepository> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View = inflater.inflate(R.layout.dialog_shopping_add, container, false)

        val button: Button = root.findViewById<Button>(R.id.alert_add_button)
        val title_field = root.findViewById<TextInputEditText>(R.id.alert_title_input)

        button.setOnClickListener {
            shoppingListRepository.addItem(
                ShoppingListItem(
                    id = UUID.randomUUID().toString(),
                    text = title_field.text.toString(),
                    checked = false,
                    amount = 1))

            dismiss()
        }
        return root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }
}