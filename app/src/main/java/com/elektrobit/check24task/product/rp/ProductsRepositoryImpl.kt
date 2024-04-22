package com.elektrobit.check24task.product.rp

import com.elektrobit.check24task.product.data.remote.ProductsDataSource
import com.elektrobit.check24task.product.mapper.toUiEntity
import com.elektrobit.check24task.product.rp.entity.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsRepositoryImpl(private val productsDataSource: ProductsDataSource) :
    ProductsRepository {
    override fun getProducts(): Flow<Products> {
        return productsDataSource.getProducts().map { it.toUiEntity() }
    }
}
