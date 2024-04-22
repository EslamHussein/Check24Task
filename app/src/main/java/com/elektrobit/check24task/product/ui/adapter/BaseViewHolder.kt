package com.elektrobit.check24task.product.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ITEM>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: ITEM)
}
