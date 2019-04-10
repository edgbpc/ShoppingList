package edu.eric.goodwin.shoppinglist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.*
import org.w3c.dom.Text

class DBHelper(context: Context): ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ShoppingListsSchema.TABLE_NAME, true,
            ShoppingListsSchema.Cols.iID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ShoppingListsSchema.Cols.cITEM to TEXT,
            ShoppingListsSchema.Cols.iCOUNT to TEXT,
            ShoppingListsSchema.Cols.cSTORE to TEXT,
            ShoppingListsSchema.Cols.iPRICE to TEXT,
            ShoppingListsSchema.Cols.lPURCHASED to TEXT //0 false, 1 true

            )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    companion object {
        const val DB_NAME = "ShoppingList.sqLite"
        const val DB_VERSION = 1
    }

}
