package com.muhammad_ammar.tentwenty.view.fragments.get_tickets

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.adapter.MovieAdapter
import com.muhammad_ammar.tentwenty.koinDI.watchModule
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.fragments.base.MainMVVMNavigationFragment
import com.muhammad_ammar.tentwenty.extensions.setupProgressDialog
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.UpcomingMoviesModelResponse
import com.muhammad_ammar.tentwenty.view.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_tickets_layout.*
import kotlinx.android.synthetic.main.fragment_watch.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module

class GetTicketsFragment :
    BaseFragment() {


    override fun getLayoutResId() = R.layout.fragment_get_tickets_layout


    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_btn.setOnClickListener {
            findNavController().navigateUp()
        }

    }


}