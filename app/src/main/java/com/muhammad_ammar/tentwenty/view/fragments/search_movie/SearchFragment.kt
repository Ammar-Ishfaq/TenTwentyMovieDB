package com.muhammad_ammar.tentwenty.view.fragments.search_movie

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.fragments.base.MainMVVMNavigationFragment
import com.muhammad_ammar.tentwenty.extensions.setupProgressDialog
import com.muhammad_ammar.tentwenty.koinDI.searchModule
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammad_ammar.tentwenty.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment :
    MainMVVMNavigationFragment<SearchFragmentViewModel>(SearchFragmentViewModel::class) {


    override fun getLayoutResId() = R.layout.fragment_search

    override fun registerModule(): Module = searchModule
    private val navArgs: SearchFragmentArgs by navArgs()

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(requireContext())
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)
        navArgs.upcomingMovie
        lifecycleScope.launch {
            viewModel.getGenere();
        }
        attachedViewModel()
    }

    private fun attachedViewModel() {
        observe(viewModel.genereResponse) {
            if (!it.consumed) it.consume()?.let {
                val filterList = viewModel.getFilteredData(it, navArgs.upcomingMovie.results)
                searchAdapter.submitList(filterList)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_search_close.setOnClickListener {
            findNavController().navigateUp()
        }
        setAdapter()

    }

    private fun setAdapter() {
        fragment_search_rv?.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = searchAdapter
        }
    }

}