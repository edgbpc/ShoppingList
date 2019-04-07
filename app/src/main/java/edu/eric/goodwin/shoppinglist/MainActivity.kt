package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.fab

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

        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }




}
