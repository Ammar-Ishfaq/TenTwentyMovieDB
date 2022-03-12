package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.viewmodel.AllUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val alluserModule = module {
    viewModel {
        AllUserViewModel(
            get()
        )
    }
}