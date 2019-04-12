package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_child.*
import kotlinx.android.synthetic.main.fragment_parent.*

class ChildShoppingListViewFragment: Fragment() {

    var data: String = String()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_child, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var displayData = (ShoppingListModel(activity!!).persistence.shoppingListFor(data))

        childFab.setOnClickListener { view ->
            val fm = activity!!.supportFragmentManager
            val dialogFragment = DialogBoxFragment()
            dialogFragment.show(fm, data.toString())

        }

        childShoppingListFragmentView.layoutManager = LinearLayoutManager(this.activity)
        childShoppingListFragmentView.adapter = ChildListAdapter(displayData!!)
        setRecyclerViewItemTouchListener(displayData!!)



    }

    inner class ChildListAdapter(val data: List<ShoppingList>): RecyclerView.Adapter<ChildListAdapter.ChildListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildListHolder {
            val inflater = LayoutInflater.from(activity)
            val itemView = inflater.inflate(R.layout.child_list_layout, parent, false)
            return ChildListHolder(itemView)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: ChildListHolder, position: Int) {
            holder.itemTextView.text = "Item: " + data.get(position).cItem
            holder.qtyTextView.text = "Quantity: " + data.get(position).iCount.toString()
            holder.priceTextView.text = "$" + data.get(position).iPrice.toString()
        }

        inner class ChildListHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener  {
            private var view: View = itemView


            val itemTextView: TextView = itemView.findViewById(R.id.itemTitleView)
            val qtyTextView: TextView = itemView.findViewById(R.id.quantityTextView)
            val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

            init {
                itemView.setOnClickListener(this)

            }

            override fun onClick(v: View?) {
                Log.i("onclick", "onCLick clicked")
                val position = childShoppingListFragmentView.getChildLayoutPosition(this.view)
                val fm = activity!!.supportFragmentManager
                val editDialogBoxFragment = EditBoxFragment()
                editDialogBoxFragment.show(fm, data[position].iId.toString())
            }

        }
    }

    fun setRecyclerViewItemTouchListener(data: List<ShoppingList>) {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int){
                val position = viewHolder.adapterPosition
                (ShoppingListModel(activity!!).persistence.deleteChildItem(data[position]))
                var refreshData = (ShoppingListModel(activity!!).persistence.shoppingListFor(data.get(position).cStore!!))
                childShoppingListFragmentView.adapter = ChildListAdapter(refreshData!!)


            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(childShoppingListFragmentView)

    }



}
