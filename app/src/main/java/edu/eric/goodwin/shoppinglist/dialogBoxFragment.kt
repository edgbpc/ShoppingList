package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.child_list_layout.*
import kotlinx.android.synthetic.main.child_list_layout.view.*
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_parent.*
import org.jetbrains.anko.sdk27.coroutines.textChangedListener

class dialogBoxFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container)



        var qtyEditText = view.findViewById(R.id.QtyEditText) as EditText
        var priceEditText = view.findViewById(R.id.priceEditText) as EditText

        var nameEditText = view.findViewById(R.id.nameEditText) as EditText


        view.createButton.setOnClickListener {

            if (this.tag == "dialog_box_call_from_parent") {


                var nameToSave = nameEditText.text
                (ShoppingListModel(getActivity()!!).persistence.createParentItemWith(nameToSave.toString()))
                Log.i("create button", nameToSave.toString())
                // need to update list

                super.dismiss()
            } else {
                var nameToSave = nameEditText.text
                var qtyToSave = qtyEditText.text
                var priceToSave = priceEditText.text
                (ShoppingListModel(getActivity()!!).persistence.addChildItem(ShoppingList(null, nameToSave.toString(), qtyToSave.toString(), this.tag, priceToSave.toString())))
                super.dismiss()

            }
        }

        view.cancelButton.setOnClickListener {
            Log.d("Cancel Button", "Cancel button pressed")
            super.dismiss()
        }

        view.nameEditText.textChangedListener {
            var textToSave = nameEditText.text
        }

        if (this.tag == "dialog_box_call_from_parent") {

            qtyEditText.visibility = View.GONE
            priceEditText.visibility = View.GONE

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
