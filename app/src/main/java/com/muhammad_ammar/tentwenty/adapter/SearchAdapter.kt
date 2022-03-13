package com.muhammad_ammar.tentwenty.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.Result
import com.muhammad_ammar.tentwenty.util.AppUtils.Companion.BaseImageUrl
import kotlinx.android.synthetic.main.raw_movie_items.view.*

class SearchAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    private var movieList = mutableListOf<Result>()

    inner class SearchAdapterViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ImageView = itemView.rv_movie_img
        val title = itemView.rv_movie_text
        val imageProgress = itemView.rv_movie_progress_bar
    }

    public fun submitList(list: List<Result>) {
        movieList = list as MutableList<Result>
        notifyDataSetChanged()
    }

    private val mInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapterViewHolder {
        val view = mInflater.inflate(R.layout.raw_search_items, parent, false)
        return SearchAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        val items = movieList[position]
        holder.title.text = items.selectedForGenreName

        if (items.poster_path.isNotEmpty())
            Glide.with(context)
                .load(BaseImageUrl + items.poster_path)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.imageProgress.visibility = View.GONE
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
                        holder.imageProgress.visibility = View.GONE
                        return false
                    }

                })
                .into(holder.ImageView)
        else {
            holder.imageProgress.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}