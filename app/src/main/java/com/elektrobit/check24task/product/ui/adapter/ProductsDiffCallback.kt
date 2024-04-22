package com.elektrobit.check24task.product.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elektrobit.check24task.product.rp.entity.Product

class ProductsDiffCallback(
    private val newProducts: List<Product>,
    private val oldProducts: List<Product>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldProducts.size
    }

    override fun getNewListSize(): Int {
        return newProducts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProducts[oldItemPosition].id == newProducts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProducts[oldItemPosition] == newProducts[newItemPosition]
    }
}
