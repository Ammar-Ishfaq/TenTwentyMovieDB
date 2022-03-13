package com.muhammad_ammar.tentwenty.backend

import android.os.Build
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.muhammad_ammar.tentwenty.BuildConfig.API_KEY
import com.muhammad_ammar.tentwenty.koinDI.retrofitModule
import com.muhammad_ammar.tentwenty.koinDI.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * For the starting of KoinDI
 */
class MyCustomApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyCustomApp)
            modules(
                listOf(
                    retrofitModule, utilModule
                )
            )
        }
    }

    fun getApiKey(): String {
        return API_KEY
    }
}