package edu.eric.goodwin.shoppinglist

import android.content.Context
import android.util.Log
import edu.eric.goodwin.shoppinglist.database.DBHelper
import edu.eric.goodwin.shoppinglist.database.ShoppingListsPersistence

class ShoppingListModel (context: Context) {

    var persistence: ShoppingListsPersistence

    init {
        val dbHelper = DBHelper(context.applicationContext)
        persistence = ShoppingListsPersistence(dbHelper)
   //     persistence.createParentItemWith("Walmart")



//
//        var _test: List<ShoppingList> = persistence.getParentLists()
//        var _test2: List<ShoppingList>? = persistence.shoppingListFor("Walmart")
//        var _test3: List<ShoppingList>? = persistence.getAllLists()

        Log.d("persistence", "message")
    }

    constructor(listData: ShoppingList, context: Context):this(context){

        persistence.createParentItemWith("Walmart")

      //  var _test: List<ShoppingList> = persistence.getParentLists()

    }







}