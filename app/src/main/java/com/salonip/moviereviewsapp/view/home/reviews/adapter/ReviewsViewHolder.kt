package com.salonip.moviereviewsapp.view.home.reviews.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.view.home.common.OnReviewClickListener
import com.bumptech.glide.Glide
import com.salonip.moviereviewsapp.R

class ReviewsViewHolder(
    itemView: View,
    onReviewClickListener: OnReviewClickListener,
    reviews: ArrayList<Review>
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val positionRacy = adapterPosition
            if (positionRacy != RecyclerView.NO_POSITION) {
                onReviewClickListener.onItemClick(reviews[positionRacy])
            }
        }
    }

    private val tvTitleReview = itemView.findViewById(R.id.tv_title_favorite) as TextView
    private val tvHeadlineReview = itemView.findViewById(R.id.tv_headline_favorite) as TextView
    private val tvPublicationDate =
        itemView.findViewById(R.id.tv_publication_date_review) as TextView
    private val imgMovieReview = itemView.findViewById(R.id.img_listitem_imageView) as ImageView

    fun bind(review: Review) {
        tvTitleReview.text = review.title
        tvHeadlineReview.text = review.headline
        tvPublicationDate.text = review.publicationDate
        Glide.with(itemView)
            .load(review.src)
            .centerCrop()
            .placeholder(R.drawable.image_header_details)
            .into(imgMovieReview)
    }
}