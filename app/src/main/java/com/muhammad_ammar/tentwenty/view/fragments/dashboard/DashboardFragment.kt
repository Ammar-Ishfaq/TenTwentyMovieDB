package com.muhammad_ammar.tentwenty.view.fragments.dashboard

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.koinDI.dashboardModule
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.fragments.base.MainMVVMNavigationFragment
import com.muhammad_ammar.tentwenty.extensions.setupProgressDialog
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module


class DashboardFragment :
    MainMVVMNavigationFragment<DashboardViewModel>(DashboardViewModel::class) {


    override fun getLayoutResId(): Int = R.layout.fragment_dashboard

    override fun registerModule(): Module {
        return dashboardModule
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)

        lifecycleScope.launch {
            attachViewModel()
        }
    }

    private fun attachViewModel() {

        observe(viewModel.snackbarMessage) {
            val msg = it?.consume()
            if (!msg.isNullOrEmpty()) {
                showAlertDialog(msg)

            }
        }

    }

}