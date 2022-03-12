package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.view.fragments.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    viewModel {
        DashboardViewModel(
            get()
        )
    }
}