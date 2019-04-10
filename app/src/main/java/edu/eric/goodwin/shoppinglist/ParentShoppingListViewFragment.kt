package edu.eric.goodwin.shoppinglist

import android.app.Activity
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
import edu.eric.goodwin.shoppinglist.database.ShoppingListsSchema
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_parent.*


class ParentShoppingListViewFragment: Fragment() {

    interface Listener{
        fun fabButtonPushed()
    }

    var listener: Listener? = null

    private var childShoppingListViewFragment: ChildShoppingListViewFragment? = null

    private lateinit var model: ShoppingListModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_parent, container, false)
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
  //      model = ShoppingListModel(getActivity()!!)

    //    var data = model.persistence.getParentLists()

   //     data = ShoppingListModel(getActivity()!!).persistence.getParentLists()

        fab.setOnClickListener { view ->
            listener?.fabButtonPushed()
//            val fm = getActivity()!!.supportFragmentManager
//            val dialogBoxFragment = dialogBoxFragment()
//            dialogBoxFragment.show(fm, "dialog_box_call_from_parent")

        }

        parentShoppingListFragmentView.layoutManager = LinearLayoutManager(this.activity)
        parentShoppingListFragmentView.adapter = ParentListAdapter(ShoppingListModel(getActivity()!!).persistence.getParentLists())
        setRecyclerViewItemTouchListener(ShoppingListModel(getActivity()!!).persistence.getParentLists())
    }

    inner class ParentListAdapter(val data: List<ShoppingList>): RecyclerView.Adapter<ParentListAdapter.ParentListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentListHolder {
            val inflater = LayoutInflater.from(activity)
            val itemView = inflater.inflate(R.layout.parent_list_layout, parent, false)
            return ParentListHolder(itemView)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: ParentListHolder, position: Int) {

          holder.titleTextView.text = data.get(position).cStore
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
                val newListTerm = data.get(position).cStore

                childShoppingListViewFragment = getActivity()?.supportFragmentManager?.findFragmentById(R.id.fragmentContainer) as? ChildShoppingListViewFragment

                if (childShoppingListViewFragment == null) {
                    childShoppingListViewFragment = ChildShoppingListViewFragment()
                    childShoppingListViewFragment?.data = newListTerm!!
                    getActivity()?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainer, childShoppingListViewFragment!!)
                        ?.addToBackStack(null)
                        ?.commit()
                }

            }

        }

    }

    //bad
    // not sure happy with this solution

    fun setRecyclerViewItemTouchListener(data: List<ShoppingList>) {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int){
                val position = viewHolder.adapterPosition
                (ShoppingListModel(getActivity()!!).persistence.deleteParentList(ShoppingListModel(getActivity()!!).persistence.getParentLists()[position]))
                parentShoppingListFragmentView.adapter = ParentListAdapter(ShoppingListModel(getActivity()!!).persistence.getParentLists())


            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(parentShoppingListFragmentView)

    }


}


