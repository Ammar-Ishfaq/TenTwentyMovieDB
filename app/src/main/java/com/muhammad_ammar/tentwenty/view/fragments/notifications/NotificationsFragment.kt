package com.muhammad_ammar.tentwenty.view.fragments.notifications

import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.fragments.base.MainMVVMNavigationFragment
import com.muhammad_ammar.tentwenty.extensions.setupProgressDialog
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NotificationsFragment :
    MainMVVMNavigationFragment<NotificationsViewModel>(NotificationsViewModel::class) {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_notifications
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