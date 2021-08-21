package com.cubesolver.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cubesolver.shoppinglist.R
import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem
import com.cubesolver.shoppinglist.ui.shoppinglist.AddDialogListener
import com.cubesolver.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    public val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.itemView.tvName.text = curShoppingItem.name
        var amountText: String

        if (curShoppingItem.amount == 0) { amountText = "" } else { amountText = "${curShoppingItem.amount}" }

        holder.itemView.tvAmount.text = amountText

        holder.itemView.tvAmount.setOnClickListener {
            AmountItemAdapter(curShoppingItem, holder.itemView.context,
                object : AddDialogListener{
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.update(item)
                        holder.itemView.tvAmount.text = "${curShoppingItem.amount}"
                        viewModel.update(curShoppingItem)
                    }
                }).show()
        }

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}