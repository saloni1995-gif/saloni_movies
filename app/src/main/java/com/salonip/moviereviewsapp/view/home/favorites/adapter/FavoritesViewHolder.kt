package com.salonip.moviereviewsapp.view.home.favorites.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.view.home.common.OnReviewClickListener
import com.bumptech.glide.Glide
import com.salonip.moviereviewsapp.R

class FavoritesViewHolder(
    itemView: View,
    onReviewClickListener: OnReviewClickListener,
    reviews: List<Review>
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val positionRcy = adapterPosition
            if (positionRcy != RecyclerView.NO_POSITION) {
                onReviewClickListener.onItemClick(reviews[positionRcy])
            }
        }
    }

    private val tvTitleFavorite = itemView.findViewById(R.id.tv_title_favorite) as TextView
    private val tvHeadlineFavorite = itemView.findViewById(R.id.tv_headline_favorite) as TextView
    private val imgMovieFavorite = itemView.findViewById(R.id.img_item_favorite) as ImageView

    fun bind(review: Review) {
        tvTitleFavorite.text = review.title
        tvHeadlineFavorite.text = review.headline
        Glide.with(itemView)
            .load(review.src)
            .centerCrop()
            .placeholder(R.drawable.image_header_details)
            .into(imgMovieFavorite)
    }

}