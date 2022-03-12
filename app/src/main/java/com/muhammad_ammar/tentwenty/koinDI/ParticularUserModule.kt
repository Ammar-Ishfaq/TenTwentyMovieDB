package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.viewmodel.ParticularUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val particularUserModule = module {
    viewModel {
        ParticularUserViewModel(
            get()
        )
    }
}