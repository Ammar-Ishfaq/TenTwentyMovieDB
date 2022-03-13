package com.muhammad_ammar.tentwenty.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.models.movie_details.Genre
import kotlinx.android.synthetic.main.raw_movie_items.view.*
import kotlinx.android.synthetic.main.row_genre.view.*
import kotlin.random.Random

class GenreAdapter(private val context: Context) :
    RecyclerView.Adapter<GenreAdapter.NotificationAdapterViewHolder>() {

    private var genre = mutableListOf<Genre>()

    inner class NotificationAdapterViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val genre_row_text = itemView.genre_row_text
        val genre_card = itemView.genre_card
    }

    public fun submitList(
        genres: List<Genre>
    ) {
        genre = genres as MutableList<Genre>
        notifyDataSetChanged()
    }

    private val mInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapterViewHolder {
        val view = mInflater.inflate(R.layout.row_genre, parent, false)
        return NotificationAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationAdapterViewHolder, position: Int) {
        val items = genre[position]
        holder.genre_row_text.text = items.name
        holder.genre_card.setCardBackgroundColor(Int.randomColor())

    }

    override fun getItemCount(): Int {
        return genre.size
    }

    fun Int.Companion.randomColor(): Int {
        return Color.argb(
            255,
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
    }
}