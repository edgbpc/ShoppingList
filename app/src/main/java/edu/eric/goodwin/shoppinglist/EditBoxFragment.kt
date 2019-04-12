package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_edit.view.*

class EditBoxFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container)

        val qtyEditText = view.findViewById(R.id.QtyEditText) as EditText
        val priceEditText = view.findViewById(R.id.priceEditText) as EditText
        val nameEditText = view.findViewById(R.id.nameEditText) as EditText

        var iId = this.tag!!.toInt()
        var data = (ShoppingListModel(context!!).persistence.shoppingListFor(iId))
        nameEditText.setText(data!!.cItem)
        qtyEditText.setText(data!!.iCount)
        priceEditText.setText(data!!.iPrice)

        view.editEditButton.setOnClickListener {
            //delete previous list in that location
            ShoppingListModel(context!!).persistence.deleteChildItem(data)
            //add new list
            ShoppingListModel(context!!).persistence.addChildItem(ShoppingList(null, nameEditText.text.toString(), qtyEditText.text.toString(), data!!.cStore, priceEditText.text.toString()))

            super.dismiss()
        }

        view.editCancelButton.setOnClickListener {
            Log.d("Cancel Button", "Cancel button pressed")
            super.dismiss()
        }






        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}


