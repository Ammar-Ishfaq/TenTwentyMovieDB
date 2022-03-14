package com.muhammad_ammar.tentwenty.view.fragments.moview_detail

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
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
import com.muhammad_ammar.tentwenty.koinDI.movieDetailModule
import com.muhammad_ammar.tentwenty.models.movie_details.MovieDetailResponse
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.muhammad_ammar.tentwenty.adapter.GenreAdapter
import com.muhammad_ammar.tentwenty.util.AppUtils
import kotlinx.android.synthetic.main.fragment_movie_detial.*

class MovieDetailFragment :
    MainMVVMNavigationFragment<MovieDetailFragmentViewModel>(MovieDetailFragmentViewModel::class) {

    private val navArgs: MovieDetailFragmentArgs by navArgs()

    override fun getLayoutResId() = R.layout.fragment_movie_detial
    override fun registerModule(): Module {
        return movieDetailModule
    }

    private lateinit var movieDetailResponse: MovieDetailResponse
    private val movieGenreAdapter: GenreAdapter by lazy {
        GenreAdapter(requireContext())
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)
        lifecycleScope.launch {
            viewModel.getMovieDetail(navArgs.movieId.toInt());
        }
        attachedViewModel()
    }

    private fun attachedViewModel() {
        observe(viewModel.movieDetailResponse) {
            if (!it.consumed) it.consume()?.let { it1 ->
                movieDetailResponse = it1
                fragment_movie_detail_description.text = movieDetailResponse.overview
                movieGenreAdapter.submitList(movieDetailResponse.genres)
                loadImage(movieDetailResponse.poster_path)
            }
        }
    }

    private fun loadImage(imgUrl: String) {
        Glide.with(requireContext())
            .load(AppUtils.BaseImageUrl + imgUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    fragment_movie_detail_progress_bar.visibility = View.GONE
                    return false
                }

                @SuppressLint("CheckResult")
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    fragment_movie_detail_progress_bar.visibility = View.GONE
                    return false
                }

            })
            .into(fragment_movie_details_movieimage)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        fragment_movie_back_btn.setOnClickListener {
            findNavController().navigateUp()
        }
        fragment_movie_detail_get_ticket.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToGetTicketsFragment())
        }
    }

    private fun setAdapter() {
        genre_type_recyclerview?.let {
            it.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.adapter = movieGenreAdapter
        }
    }

}