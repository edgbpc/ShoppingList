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

        //putting some data in for testing
//        persistence.addChildItem(ShoppingList(null, "Tacos", 5, "Walmart", 3))
//        persistence.addChildItem(ShoppingList(null, "Beef", 3, "Trader Joes", 5))
//        persistence.addChildItem(ShoppingList(null, "Pork", 5, "Trader joes", 3))
//        persistence.addChildItem(ShoppingList(null, "Fish", 5, "Walmart", 6))
//        persistence.addChildItem(ShoppingList(null, "Tissues", 1, "QT", 1))
//        persistence.addChildItem(ShoppingList(null, "Iphone", 1, "BestBuy", 1000))
//        persistence.addChildItem(ShoppingList(null, "TV", 5, "Amazon", 299))


        var _test: List<ShoppingList> = persistence.getParentLists()
        var _test2: List<ShoppingList>? = persistence.shoppingListFor("Walmart")
        var _test3: List<ShoppingList>? = persistence.getAllLists()

        Log.d("persistence", "message")
    }

    constructor(listData: ShoppingList, context: Context):this(context){

        persistence.createParentItemWith("Walmart")

        var _test: List<ShoppingList> = persistence.getParentLists()

    }







}