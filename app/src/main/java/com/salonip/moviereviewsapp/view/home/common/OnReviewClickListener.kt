package com.salonip.moviereviewsapp.view.home.common

import com.salonip.moviereviewsapp.data.domain.Review

interface OnReviewClickListener {
    fun onItemClick(review: Review)
}