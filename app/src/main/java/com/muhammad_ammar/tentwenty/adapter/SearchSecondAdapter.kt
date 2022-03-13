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
import com.muhammad_ammar.tentwenty.adapter.SearchSecondAdapter.*
import com.muhammad_ammar.tentwenty.models.genere.Genre
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.Result
import com.muhammad_ammar.tentwenty.util.AppUtils.Companion.BaseImageUrl
import kotlinx.android.synthetic.main.raw_search_second_items.view.*

class SearchSecondAdapter(private val context: Context, private val mListener: (Result) -> Unit) :
    RecyclerView.Adapter<SearchAdapterViewHolder>() {

    private var movieList = mutableListOf<Result>()
    private var genreList = mutableListOf<Genre>()

    inner class SearchAdapterViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ImageView = itemView.rv_movie_img
        val title = itemView.rv_movie_text
        val genre = itemView.rv_movie_genre
        val imageProgress = itemView.fragment_movie_detail_progress_bar
    }

    fun submitList(list: List<Result>, genreList: List<Genre>) {
        movieList = list as MutableList<Result>
        this.genreList = genreList as MutableList<Genre>
        notifyDataSetChanged()
    }

    private val mInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapterViewHolder {
        val view = mInflater.inflate(R.layout.raw_search_second_items, parent, false)
        return SearchAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        val items = movieList[position]
        holder.title.text = items.title

        holder.genre.text = getGenresOfMovie(items.genre_ids, genreList)
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
        holder.itemView.setOnClickListener {
            mListener.invoke(items) // <- item instance of ItemObject
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    private fun getGenresOfMovie(items: List<Int>, genreList: List<Genre>): StringBuilder {
        val genString = StringBuilder()
        items.forEachIndexed { index, item ->
            val nameGenre = genreList.filter { it.id == item }[0].name
            if (nameGenre.isNotEmpty())
                genString.append("$nameGenre ")
        }
        return genString
    }

}