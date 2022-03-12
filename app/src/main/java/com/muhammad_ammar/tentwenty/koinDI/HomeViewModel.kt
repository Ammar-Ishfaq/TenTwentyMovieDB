package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.view.fragments.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeFragmentViewModel(
            get()
        )
    }
}