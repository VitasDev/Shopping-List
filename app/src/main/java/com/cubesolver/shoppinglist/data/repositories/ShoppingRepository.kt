package com.cubesolver.shoppinglist.data.repositories

import com.cubesolver.shoppinglist.data.db.ShoppingDatabase
import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun update(item: ShoppingItem) = db.getShoppingDao().update(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}