package edu.eric.goodwin.shoppinglist

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import edu.eric.goodwin.shoppinglist.database.DBHelper


class MainActivity : AppCompatActivity(), ParentShoppingListViewFragment.Listener {

    companion object {
        const val PARENT_SHOP_LIST_FRAG_TAG = "ParentShoppingList"
        const val CHILD_SHOP_LIST_FRAG_TAG = "ChildShoppingList"

    }

    private var parentShoppingListViewFragment: ParentShoppingListViewFragment? = null
    private var childShoppingListViewFragment: ChildShoppingListViewFragment? = null
    private lateinit var model: ShoppingListModel


    override fun fabButtonPushed() {
        val fm = supportFragmentManager
        val dialogBoxFragment = dialogBoxFragment()
        dialogBoxFragment.show(fm, "dialog_box_call_from_parent")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ShoppingListModel(this)

    //    childShoppingListViewFragment?.listener = this


      // var _test1 = model.persistence.getAllLists()

        //populate some database data to test with
//        model.persistence.addChildItem(ShoppingList(null, "Tacos", 5, "Walmart", 3))
//        model.persistence.addChildItem(ShoppingList(null, "Beef", 3, "Trader Joes", 5))
//        model.persistence.addChildItem(ShoppingList(null, "Pork", 5, "Trader joes", 3))
//        model.persistence.addChildItem(ShoppingList(null, "Fish", 5, "Walmart", 6))
//        model.persistence.addChildItem(ShoppingList(null, "Tissues", 1, "QT", 1))
//        model.persistence.addChildItem(ShoppingList(null, "Iphone", 1, "BestBuy", 1000))
//        model.persistence.addChildItem(ShoppingList(null, "TV", 5, "Amazon", 299))


      //  model.persistence.createParentItemWith("Taco Bell 2")


        parentShoppingListViewFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? ParentShoppingListViewFragment
        if (parentShoppingListViewFragment == null) {
            parentShoppingListViewFragment = ParentShoppingListViewFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, parentShoppingListViewFragment!!)
                .commit()
        }

        parentShoppingListViewFragment?.listener = this



    }




}
