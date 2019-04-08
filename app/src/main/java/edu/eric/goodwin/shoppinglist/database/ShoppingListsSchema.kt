package edu.eric.goodwin.shoppinglist.database

import android.provider.BaseColumns

object ShoppingListsSchema {
    const val TABLE_NAME = "Shopping_List"
    object Cols {
        // Following naming convention I use at work.  i = int, c = character, l = boolean

        const val iID = "iId"
        const val cITEM = "cItem"
        const val iCOUNT = "iCount"
        const val cSTORE = "cStore"
        const val iPRICE = "iPrice"
        const val lPURCHASED = "lPurchased"

    }
}