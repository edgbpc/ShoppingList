package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_parent.*

class DialogBoxFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container)

//

        val qtyEditText = view.findViewById(R.id.QtyEditText) as EditText
        val priceEditText = view.findViewById(R.id.priceEditText) as EditText
        val nameEditText = view.findViewById(R.id.nameEditText) as EditText


        view.createButton.setOnClickListener {

            if (this.tag == "dialog_box_call_from_parent") {


                val nameToSave = nameEditText.text
                (ShoppingListModel(context!!).persistence.createParentItemWith(nameToSave.toString()))
                Log.i("create button", nameToSave.toString())
                // need to update list

                dismiss()

            } else {

                val nameToSave = nameEditText.text
                val qtyToSave = qtyEditText.text
                val priceToSave = priceEditText.text
                (ShoppingListModel(context!!).persistence.addChildItem(ShoppingList(null, nameToSave.toString(), qtyToSave.toString(), this.tag, priceToSave.toString())))
                dismiss()

            }
        }

        view.cancelButton.setOnClickListener {
            Log.d("Cancel Button", "Cancel button pressed")
            super.dismiss()
        }

        if (this.tag == "dialog_box_call_from_parent") {

            qtyEditText.visibility = View.GONE
            priceEditText.visibility = View.GONE

        }else {
            //do nothing different
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
