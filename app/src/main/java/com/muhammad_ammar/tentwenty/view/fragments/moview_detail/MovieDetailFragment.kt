package com.muhammad_ammar.tentwenty.view.fragments.moview_detail

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
import com.muhammad_ammar.tentwenty.koinDI.movieDetailModule
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.UpcomingMoviesModelResponse
import kotlinx.android.synthetic.main.fragment_watch.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module

class MovieDetailFragment :
    MainMVVMNavigationFragment<MovieDetailFragmentViewModel>(MovieDetailFragmentViewModel::class) {


    override fun getLayoutResId() = R.layout.fragment_movie_detial
    override fun registerModule(): Module {
        return movieDetailModule
    }

    private lateinit var upcomingMoviesModelResponse: UpcomingMoviesModelResponse
    private val notificationAdapter: MovieAdapter by lazy {
        MovieAdapter(requireContext())
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)
        lifecycleScope.launch {
            viewModel.getAllUpcomingMovies();
        }
        attachedViewModel()
    }

    private fun attachedViewModel() {
        observe(viewModel.upComingMovie) {
            if (!it.consumed) it.consume()?.let { it1 ->
                upcomingMoviesModelResponse = it1
                notificationAdapter.submitList(upcomingMoviesModelResponse.results)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()


    }

    private fun setAdapter() {
        fragment_watch_rv?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = notificationAdapter
        }
    }

}