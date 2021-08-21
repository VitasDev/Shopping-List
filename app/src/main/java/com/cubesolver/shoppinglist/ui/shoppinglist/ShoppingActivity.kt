package com.cubesolver.shoppinglist.ui.shoppinglist

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cubesolver.shoppinglist.R
import com.cubesolver.shoppinglist.data.db.entities.ShoppingItem
import com.cubesolver.shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)
        val adapter =  ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter
        val lyt_failed = findViewById<View>(R.id.lyt_empty)

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it

            if (adapter.items.isEmpty()) {
                lyt_failed.visibility = View.VISIBLE
            } else {
                lyt_failed.visibility = View.GONE
            }

            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                    object : AddDialogListener {
                        override fun onAddButtonClicked(item: ShoppingItem) {
                            viewModel.upsert(item)
                        }
                    }).show()
        }
    }
}