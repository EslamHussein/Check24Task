package com.elektrobit.check24task.product.di

import com.elektrobit.check24task.product.data.remote.BASE_URL
import com.elektrobit.check24task.product.data.remote.CONNECT_TIMEOUT
import com.elektrobit.check24task.product.data.remote.ProductsDataSource
import com.elektrobit.check24task.product.data.remote.ProductsDataSourceImpl
import com.elektrobit.check24task.product.data.remote.ProductsService
import com.elektrobit.check24task.product.data.remote.READ_TIMEOUT
import com.elektrobit.check24task.product.data.remote.WRITE_TIMEOUT
import com.elektrobit.check24task.product.presentation.ProductsViewModel
import com.elektrobit.check24task.product.rp.ProductsRepository
import com.elektrobit.check24task.product.rp.ProductsRepositoryImpl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val productModule = module {

    single<ProductsService> {
        get<Retrofit>().create(ProductsService::class.java)
    }

    single<ProductsDataSource> { ProductsDataSourceImpl(get()) }
    single<ProductsRepository> { ProductsRepositoryImpl(get()) }

    viewModel { ProductsViewModel(get()) }
}
