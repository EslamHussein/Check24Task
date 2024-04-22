package com.elektrobit.check24task.product.data.remote

import com.elektrobit.check24task.product.data.remote.entity.ProductsRemoteResponse
import retrofit2.http.GET

interface ProductsService {
    @GET("/products-test.json")
    suspend fun getProducts(): ProductsRemoteResponse
}
