package edu.eric.goodwin.shoppinglist.database

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import edu.eric.goodwin.shoppinglist.ShoppingList
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insertOrThrow
import org.jetbrains.anko.db.select

class ShoppingListsPersistence(private val dbHelper: DBHelper) {


    //will return entire table
    fun getAllLists(): List<ShoppingList> {
        return dbHelper.use {
            select(ShoppingListsSchema.TABLE_NAME)
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

    fun createParentItemWith(cStore: String): List<ShoppingList>?{
        return dbHelper.use {
            try {
                insertOrThrow(ShoppingListsSchema.TABLE_NAME, ShoppingListsSchema.Cols.cSTORE to cStore)
                shoppingListFor(cStore)
            }catch (e: SQLiteConstraintException) {
                null
            }
        }

        }

    //returns a list of items for a parent
    fun shoppingListFor(listName: String): List<ShoppingList>? {
            return dbHelper.use {
                select(ShoppingListsSchema.TABLE_NAME)
                    .whereSimple("${ShoppingListsSchema.Cols.cSTORE} = ?", listName)
                    .parseList(object : MapRowParser<ShoppingList> {
                        override fun parseRow(columns: Map<String, Any?>): ShoppingList {
                            val iId = columns[ShoppingListsSchema.Cols.iID] as Number
                            val cStore = columns[ShoppingListsSchema.Cols.cSTORE] as String
                            return ShoppingList(iId.toInt(), null, null, cStore, null, null)
                        }
                    })
            }

    }

    fun shoppingListFor(listId: Int): ShoppingList? {
        return dbHelper.use {
            select(ShoppingListsSchema.TABLE_NAME)
                .whereSimple("${ShoppingListsSchema.Cols.iID} = ?", listId.toString())
                .parseSingle(object : MapRowParser<ShoppingList> {
                    override fun parseRow(columns: Map<String, Any?>): ShoppingList {
                        val iId = columns[ShoppingListsSchema.Cols.iID] as Number
                        val cItem = columns[ShoppingListsSchema.Cols.cITEM] as String
                        val iCount = columns[ShoppingListsSchema.Cols.iCOUNT] as Number
                        val cStore = columns[ShoppingListsSchema.Cols.cSTORE] as String
                        val iPrice = columns[ShoppingListsSchema.Cols.iPRICE] as Number
                        val lPurchased = columns[ShoppingListsSchema.Cols.lPURCHASED] as Boolean
                        return ShoppingList(iId.toInt(), cItem, iCount.toInt(), cStore, iPrice.toInt(), lPurchased)
                    }
                })
        }

    }


    fun getParentLists(): List<ShoppingList> {
        return dbHelper.use {
            select(ShoppingListsSchema.TABLE_NAME)
                .parseList(object: MapRowParser<ShoppingList> {
                    override fun parseRow(columns: Map<String, Any?>): ShoppingList {
                        val iId = columns[ShoppingListsSchema.Cols.iID] as Number
                        val cStore = columns[ShoppingListsSchema.Cols.cSTORE] as String
                        return ShoppingList(iId.toInt(), null, null, cStore, null, null
                        )
                    }
                })
        }
    }

    fun deleteParentList(deleteList: ShoppingList) {
        dbHelper.use {
            delete(ShoppingListsSchema.TABLE_NAME, "${ShoppingListsSchema.Cols.iID} = ?", arrayOf(deleteList.iId.toString()))
        }
    }

    fun addChildItem(list: ShoppingList): List<ShoppingList>? {
        return dbHelper.use {
            try {
                insertOrThrow(
                    ShoppingListsSchema.TABLE_NAME,
                    ShoppingListsSchema.Cols.cITEM to list.cItem,
                    ShoppingListsSchema.Cols.cSTORE to list.cStore,
                    ShoppingListsSchema.Cols.iCOUNT to list.iCount.toString(),
                    ShoppingListsSchema.Cols.iPRICE to list.iPrice,
                    ShoppingListsSchema.Cols.lPURCHASED to list.lPurchased
                )
                itemInListWith(list.cStore!!)
            } catch (e: SQLiteConstraintException) {
                null
            }
        }
    }

    fun itemInListWith(itemName: String): List<ShoppingList>? {
        return dbHelper.use {
            select(ShoppingListsSchema.TABLE_NAME)
                .whereSimple("${ShoppingListsSchema.Cols.cITEM} = ? ", itemName)
                .parseList(object: MapRowParser<ShoppingList>{
                    override fun parseRow(columns: Map<String, Any?>): ShoppingList {
                        val iID = columns[ShoppingListsSchema.Cols.iID] as Number
                        val cItem = columns[ShoppingListsSchema.Cols.cITEM] as String
                        val cStore = columns[ShoppingListsSchema.Cols.cSTORE] as String
                        val iCount = columns[ShoppingListsSchema.Cols.iCOUNT] as String
                        val iPrice = columns[ShoppingListsSchema.Cols.iPRICE] as String
                        val lPurchased = columns[ShoppingListsSchema.Cols.lPURCHASED] as String

                        return ShoppingList(iID.toInt(), cItem, iCount.toInt(), cStore, iPrice.toInt(), lPurchased.toBoolean())

                        }
                    })
        }
    }




}


