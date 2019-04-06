package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_parent.*

class ParentShoppingListViewFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_parent, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyData: ArrayList<String> = ArrayList()

        for (i in 1..100){
            dummyData.add("dummyData " + i)
        }


        parentListingFragmentView.layoutManager = LinearLayoutManager(this.activity)
        parentListingFragmentView.adapter = ParentListAdapter(dummyData)

    }

    inner class ParentListAdapter(val dummyData: ArrayList<String>): RecyclerView.Adapter<ParentListAdapter.ParentListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentListHolder {
            val inflater = LayoutInflater.from(activity)
            val itemView = inflater.inflate(R.layout.parent_list_layout, parent, false)
            return ParentListHolder(itemView)
        }

        override fun getItemCount(): Int {
            return dummyData.size
        }

        override fun onBindViewHolder(holder: ParentListHolder, position: Int) {
        }

        inner class ParentListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            private val titleTextView: TextView = itemView.findViewById(R.id.storeTitleView)
            private val placeholderTextView: TextView = itemView.findViewById(R.id.placeholderTextview)

        }
    }




}


