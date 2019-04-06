package edu.eric.goodwin.shoppinglist.database

import android.content.ContentValues
import edu.eric.goodwin.shoppinglist.ShoppingList
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select

class ListsPersistence(private val dbHelper: DBHelper) {


    fun getAllLists(): List<ShoppingList> {
        return dbHelper.use {
            select(ShoppingListsSchema.NAME)
//                .whereSimple("cStore = ?", "Best Buy")
                .parseList(object : MapRowParser<ShoppingList> {
                    override fun parseRow(columns: Map<String, Any?>): ShoppingList {
                        val iId = columns.get(ShoppingListsSchema.Cols.iID) as Int
                        val cItem = columns.get(ShoppingListsSchema.Cols.cITEM) as String
                        val iCount = columns.get(ShoppingListsSchema.Cols.iCOUNT) as Int
                        val cStore = columns.get(ShoppingListsSchema.Cols.cSTORE) as String
                        val iPrice = columns.get(ShoppingListsSchema.Cols.iPRICE) as Int
                        val lPurchased = columns.get(ShoppingListsSchema.Cols.lPURCHASED) as Boolean
                        return ShoppingList(iId, cItem, iCount, cStore, iPrice, lPurchased)
                    }
                })
        }
    }

    fun insertItem(listData: List<ShoppingList>){
        val db = dbHelper.writableDatabase



        }
    }


