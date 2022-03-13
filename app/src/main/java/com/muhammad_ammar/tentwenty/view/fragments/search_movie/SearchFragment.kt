package com.muhammad_ammar.tentwenty.view.fragments.search_movie

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.adapter.SearchAdapter
import com.muhammad_ammar.tentwenty.adapter.SearchSecondAdapter
import com.muhammad_ammar.tentwenty.extensions.setupProgressDialog
import com.muhammad_ammar.tentwenty.koinDI.searchModule
import com.muhammad_ammar.tentwenty.models.genere.GenereResponse
import com.muhammad_ammar.tentwenty.models.genere.Genre
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.Result
import com.muhammad_ammar.tentwenty.util.MaterialDialogHelper
import com.muhammad_ammar.tentwenty.view.fragments.base.MainMVVMNavigationFragment
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_header_layout.view.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment :
    MainMVVMNavigationFragment<SearchFragmentViewModel>(SearchFragmentViewModel::class),
    TextWatcher {


    override fun getLayoutResId() = R.layout.fragment_search

    override fun registerModule(): Module = searchModule
    private val navArgs: SearchFragmentArgs by navArgs()

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(requireContext())
    }
    private val searchSecondAdapter: SearchSecondAdapter by lazy {
        SearchSecondAdapter(requireContext())
    }
    private lateinit var mainListResults: List<Result>
    private lateinit var genre: List<Genre>

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
                genre = it.genres
                mainListResults = navArgs.upcomingMovie.results
                viewModel.getGenreMerged(genre, mainListResults)
                val filterList = viewModel.getFilteredData(it, navArgs.upcomingMovie.results)
                searchAdapter.submitList(filterList)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_search_included_layout.fragment_search_close.setOnClickListener {
            findNavController().navigateUp()
        }
        fragment_search_included_layout.fragment_search_edit_text.addTextChangedListener(this)
        fragment_search_included_layout.fragment_search_edit_text.setOnFocusChangeListener { view, b ->
            if (view.isFocused) {
                setSecondAdapter()
                searchSecondAdapter.submitList(mainListResults, genre)
            } else setAdapter()
        }
        setAdapter()

    }

    private fun setAdapter() {
        fragment_search_rv?.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = searchAdapter
        }
    }

    private fun setSecondAdapter() {
        fragment_search_rv?.let {
            it.layoutManager = GridLayoutManager(requireContext(), 1)
            it.adapter = searchSecondAdapter
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val list = ArrayList(mainListResults)
        searchSecondAdapter.submitList(list.filter {
            it.title.lowercase(Locale.getDefault()).contains(
                s.toString().lowercase(Locale.getDefault())
            )
        }, genre)


    }

    override fun afterTextChanged(s: Editable?) {

    }

}