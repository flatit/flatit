package com.github.flatit.ui.shoppinglist

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.github.flatit.R

class custom_dialog_fragment :DialogFragment(){
    private lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener{
        fun on_dialgo_positive_click(dialog: DialogFragment, title_field : String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root_view: View = inflater.inflate(R.layout.dialog_shopping_add, container, false)

        val button: Button = root_view.findViewById<Button>(R.id.alert_add_button)

        button.setOnClickListener {
            listener.on_dialgo_positive_click(this, "Popcorn")
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        }catch (e: java.lang.ClassCastException){
            throw java.lang.ClassCastException((context.toString() + "must implement NoticeDialogListener"))
        }
    }
}