package com.salonip.moviereviewsapp.data.repository

import com.salonip.moviereviewsapp.data.database.dao.ReviewsFavoritesDao
import com.salonip.moviereviewsapp.data.domain.Review

class LocalDataSourceRepository(
    private val dao: ReviewsFavoritesDao
) {

    suspend fun addFavorite(review: Review) = dao.insert(review)

    suspend fun removeFavorite(review: Review) = dao.delete(review)

    suspend fun getReviews(): ArrayList<Review> = ArrayList(dao.getAll())

}