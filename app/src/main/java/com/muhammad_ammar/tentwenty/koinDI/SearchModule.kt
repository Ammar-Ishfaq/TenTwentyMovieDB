package com.muhammad_ammar.tentwenty.koinDI

import com.muhammad_ammar.tentwenty.view.fragments.search_movie.SearchFragmentViewModel
import com.muhammad_ammar.tentwenty.view.fragments.watch_movie.WatchFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchFragmentViewModel(
            get()
        )
    }
}