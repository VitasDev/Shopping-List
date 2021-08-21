package com.cubesolver.shoppinglist.ui.shoppinglist

import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}