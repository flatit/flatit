package com.github.flatit.ui.shoppinglist

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import com.github.flatit.R
import com.github.flatit.data.ShoppingListRepository
import com.github.flatit.data.model.ShoppingListItem
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.ext.android.inject

class custom_dialog_fragment() :DialogFragment(){
    private val shoppingListRepository by inject<ShoppingListRepository> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root_view: View = inflater.inflate(R.layout.dialog_shopping_add, container, false)

        val button: Button = root_view.findViewById<Button>(R.id.alert_add_button)
        val title_field = root_view.findViewById<TextInputEditText>(R.id.alert_title_input)

        button.setOnClickListener {
            /*listener.on_dialgo_positive_click(this, "Popcorn")*/
            var beislpiel: ShoppingListItem = ShoppingListItem(title_field.text.toString(), false, 1)
            shoppingListRepository.addShoppingListItems(beislpiel)
            dismiss()
        }
        return root_view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*dialog.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);*/
        return dialog
    }
}