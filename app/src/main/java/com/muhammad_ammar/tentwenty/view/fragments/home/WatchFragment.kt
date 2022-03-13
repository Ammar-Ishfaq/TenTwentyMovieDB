package com.muhammad_ammar.tentwenty.view.fragments.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.koinDI.homeModule
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.fragments.base.MainMVVMNavigationFragment
import com.muhammad_ammar.tentwenty.extensions.setupProgressDialog
import kotlinx.android.synthetic.main.fragment_watch.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module

class WatchFragment :
    MainMVVMNavigationFragment<WatchFragmentViewModel>(WatchFragmentViewModel::class) {


    override fun getLayoutResId() = R.layout.fragment_watch
    override fun registerModule(): Module {
        return homeModule
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)

        lifecycleScope.launch {
            viewModel.getAllUpcomingMovies();
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_home_dashboard.setOnClickListener {
            findNavController().navigate(
                WatchFragmentDirections.actionNavigationHomeToNavigationDashboard()
            )
        }
        fragment_home_notification.setOnClickListener {
            findNavController().navigate(
                WatchFragmentDirections.actionNavigationHomeToNavigationNotifications(
                    "A testing Passing Arguments to the Controller"
                )
            )
        }


    }
}