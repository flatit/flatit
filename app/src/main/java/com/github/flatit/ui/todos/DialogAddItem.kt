package com.github.flatit.ui.todos

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.github.flatit.R

class DialogAddItem : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // val root: View = inflater.inflate(R.layout.dialog_todos_add, container, false)

        return inflater.inflate(R.layout.dialog_todos_add, container, false)
    }
}