package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.api.SharedWebService
import com.muhammad_ammar.tentwenty.backend.MyCustomApp
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilModule = module {

    single { MaterialDialogHelper() }

    single { SharedWebService(get(), androidApplication() as MyCustomApp) }


}