package com.elektrobit.check24task.product.data.remote

import com.elektrobit.check24task.product.data.remote.entity.ProductsRemoteResponse
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    fun getProducts(): Flow<ProductsRemoteResponse>

}
