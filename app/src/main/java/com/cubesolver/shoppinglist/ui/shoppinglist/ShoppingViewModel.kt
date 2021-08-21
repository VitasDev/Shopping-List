package com.cubesolver.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem
import com.cubesolver.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
): ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun update(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}