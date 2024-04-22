package com.elektrobit.check24task.product.presentation

import com.elektrobit.check24task.product.rp.entity.Products

sealed class ProductsListState {
    data object Loading : ProductsListState()
    data class Success(val data: Products) : ProductsListState()
    data class Failure(val failureMessage: String) : ProductsListState()
}
