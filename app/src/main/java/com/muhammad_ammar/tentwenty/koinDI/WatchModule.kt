package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.view.fragments.watch_movie.WatchFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val watchModule = module {
    viewModel {
        WatchFragmentViewModel(
            get()
        )
    }
}