package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val PARENT_SHOP_LIST_FRAG_TAG = "ParentShoppingList"
        const val CHILD_SHOP_LIST_FRAG_TAG = "ChildShoppingList"
    }

    private var parentShoppingListViewFragment: ParentShoppingListViewFragment? = null
    private var childShoppingListViewFragment: ChildShoppingListViewFragment? = null

    private var shoppingListModel: ShoppingListModel? = ShoppingListModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }




}
