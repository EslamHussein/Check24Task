package com.elektrobit.check24task.product.data.remote

import com.elektrobit.check24task.product.data.remote.entity.ProductsRemoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductsDataSourceImpl(
    private val productsService: ProductsService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductsDataSource {
    override fun getProducts(): Flow<ProductsRemoteResponse> {
        return flow { emit(productsService.getProducts()) }.flowOn(dispatcher)
    }
}
