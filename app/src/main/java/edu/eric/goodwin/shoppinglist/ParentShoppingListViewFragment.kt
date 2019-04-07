package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_parent.*


class ParentShoppingListViewFragment: Fragment() {

    private var childShoppingListViewFragment: ChildShoppingListViewFragment? = null



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



        fab.setOnClickListener { view ->
            val fm = getActivity()!!.supportFragmentManager
            val dialogFragment = dialogBox()
            dialogFragment.show(fm, "dialog_box_call_from_parent")

        }


        parentShoppingListFragmentView.layoutManager = LinearLayoutManager(this.activity)
        parentShoppingListFragmentView.adapter = ParentListAdapter(dummyData)
        setRecyclerViewItemTouchListener(dummyData)
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
            holder.titleTextView.text = dummyData[position]
        }

        inner class ParentListHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
            val titleTextView: TextView = itemView.findViewById(R.id.storeTitleView)
            private var view: View = itemView

            init {
                itemView.setOnClickListener(this)

            }

            override fun onClick(v: View?) {
                Log.d("RecyclerView", "CLick!")

                val position = parentShoppingListFragmentView.getChildLayoutPosition(this.view)
                childShoppingListViewFragment = getActivity()?.supportFragmentManager?.findFragmentById(R.id.fragmentContainer) as? ChildShoppingListViewFragment



                if (childShoppingListViewFragment == null) {
                    childShoppingListViewFragment = ChildShoppingListViewFragment()
                    getActivity()?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainer, childShoppingListViewFragment!!)
                        ?.addToBackStack(null)
                        ?.commit()
            }

            }

        }

    }

    //bad

    fun setRecyclerViewItemTouchListener(dummyData: ArrayList<String>) {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int){
                val position = viewHolder.adapterPosition
                dummyData?.removeAt(position)
                parentShoppingListFragmentView.adapter!!.notifyItemRemoved(position)


            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(parentShoppingListFragmentView)

    }


}


