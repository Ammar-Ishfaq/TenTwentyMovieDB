package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.view.fragments.dashboard.DashboardViewModel
import com.muhammad_ammar.tentwenty.view.fragments.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notificationModule = module {
    viewModel {
        NotificationsViewModel(
            get()
        )
    }
}