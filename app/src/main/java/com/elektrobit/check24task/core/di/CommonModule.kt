package com.elektrobit.check24task.core.di

import com.elektrobit.check24task.product.data.remote.BASE_URL
import com.elektrobit.check24task.product.data.remote.CONNECT_TIMEOUT
import com.elektrobit.check24task.product.data.remote.READ_TIMEOUT
import com.elektrobit.check24task.product.data.remote.WRITE_TIMEOUT
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val baseNetworkModule = module {

    single {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        builder.build()
    }
    single { GsonBuilder().create() }
    single<Retrofit> {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get())).client(get()).build()
    }
}
