package com.elektrobit.check24task

import android.app.Application
import com.elektrobit.check24task.core.di.baseNetworkModule
import com.elektrobit.check24task.product.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin() {
            androidLogger()
            androidContext(this@App)
            modules(baseNetworkModule, productModule)
        }
    }
}
