package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.child_list_layout.*
import kotlinx.android.synthetic.main.child_list_layout.view.*
import kotlinx.android.synthetic.main.fragment_parent.*

class dialogBox: DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container)

        var qtyEditText = view.findViewById(R.id.QtyEditView) as EditText
        var placeholderEditText = view.findViewById(R.id.placeholderEditView) as EditText

        if (this.tag == "dialog_box_call_from_parent"){
            qtyEditText.visibility=View.GONE
            placeholderEditText.visibility=View.GONE



        }  else {

        }


        return view
    }
}