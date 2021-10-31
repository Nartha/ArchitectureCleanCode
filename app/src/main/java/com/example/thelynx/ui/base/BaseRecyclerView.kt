package com.example.thelynx.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun itemList(): MutableList<*>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    override fun getItemCount(): Int = itemList().size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (itemList().size != 0) {
            (holder as Binder<*>).bind(itemList(), position)
        }
    }

    interface Binder<in T> {
        fun bind(dataItem: Any?, position: Int)
    }
}