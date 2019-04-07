package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_parent.*

class ChildShoppingListViewFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_parent, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moreDummyData: ArrayList<String> = ArrayList()

        for (i in 1..100){
            moreDummyData.add("moreDummyData " + i)
        }

        parentShoppingListFragmentView.layoutManager = LinearLayoutManager(this.activity)
        parentShoppingListFragmentView.adapter = ChildListAdapter(moreDummyData)

    }

    inner class ChildListAdapter(val moreDummyData: ArrayList<String>): RecyclerView.Adapter<ChildListAdapter.ChildListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildListHolder {
            val inflater = LayoutInflater.from(activity)
            val itemView = inflater.inflate(R.layout.child_list_layout, parent, false)
            return ChildListHolder(itemView)
        }

        override fun getItemCount(): Int {
            return moreDummyData.size
        }

        override fun onBindViewHolder(holder: ChildListHolder, position: Int) {
            holder.itemTextView.text = moreDummyData[position]
            holder.qtyTextView.text = position.toString()
        }

        inner class ChildListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val itemTextView: TextView = itemView.findViewById(R.id.itemTitleView)
            val qtyTextView: TextView = itemView.findViewById(R.id.quantityTextView)

        }
    }




}
