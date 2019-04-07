package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    companion object {
        const val PARENT_SHOP_LIST_FRAG_TAG = "ParentShoppingList"
        const val CHILD_SHOP_LIST_FRAG_TAG = "ChildShoppingList"
    }

    private var parentShoppingListViewFragment: ParentShoppingListViewFragment? = null

    private var shoppingListModel: ShoppingListModel? = ShoppingListModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        parentShoppingListViewFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? ParentShoppingListViewFragment
        if (parentShoppingListViewFragment == null) {
            parentShoppingListViewFragment = ParentShoppingListViewFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, parentShoppingListViewFragment!!)
                .commit()
        }
    }




}
