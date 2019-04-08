package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.child_list_layout.*
import kotlinx.android.synthetic.main.child_list_layout.view.*
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_parent.*

class dialogBoxFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container)



        var qtyEditText = view.findViewById(R.id.QtyEditText) as EditText
        var placeholderEditText = view.findViewById(R.id.placeholderEditText) as EditText

        var nameEditText = view.findViewById(R.id.nameEditText) as EditText


        view.createButton.setOnClickListener {
          super.dismiss()
        }

        view.cancelButton.setOnClickListener {
            Log.d("Cancel Button", "Cancel button pressed")
            super.dismiss()
        }

        if (this.tag == "dialog_box_call_from_parent") {

            qtyEditText.visibility = View.GONE
            placeholderEditText.visibility = View.GONE

        } else {
            //do not need to do anything different
            //layout contains all fields otherwise
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
