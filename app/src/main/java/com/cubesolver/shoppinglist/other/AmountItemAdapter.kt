package com.cubesolver.shoppinglist.other

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.cubesolver.shoppinglist.R
import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem
import com.cubesolver.shoppinglist.ui.shoppinglist.AddDialogListener
import kotlinx.android.synthetic.main.amount_item.*
import kotlinx.coroutines.NonCancellable.cancel

class AmountItemAdapter(curItem: ShoppingItem, context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    val cur: ShoppingItem = curItem
    private var amountbuf: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.amount_item)

        tvAmountDetail.text = cur.amount.toString()
        amountbuf = cur.amount

        ivPlus.setOnClickListener {
            amountbuf++
            tvAmountDetail.text = amountbuf.toString()
        }

        ivMinus.setOnClickListener {
            if(amountbuf > 0) {
                amountbuf--
                tvAmountDetail.text = amountbuf.toString()
            }
        }

        tvUpdate.setOnClickListener {
            cur.amount = amountbuf
            val item = ShoppingItem(cur.name, cur.amount)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}