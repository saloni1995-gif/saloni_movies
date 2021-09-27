package com.salonip.moviereviewsapp.data.database.dao

import androidx.room.*
import com.salonip.moviereviewsapp.data.domain.Review

@Dao
interface ReviewsFavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: Review)

    @Delete
    suspend fun delete(review: Review)

    //getAll() method is used for getting all movies reviews
    @Query("SELECT * FROM moviereviews")
    suspend fun getAll(): List<Review>

}