package com.cubesolver.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.cubesolver.shoppinglist.R
import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var changeAmount: Int

            if (amount.isEmpty()) {
                changeAmount =  0
            } else {
                changeAmount = amount.toInt()
            }

            val item = ShoppingItem(name, changeAmount)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}