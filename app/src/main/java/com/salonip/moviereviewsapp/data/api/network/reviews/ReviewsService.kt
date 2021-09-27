package com.salonip.moviereviewsapp.data.api.network.reviews

import com.salonip.moviereviewsapp.data.api.network.reviews.response.ReviewsResult
import com.salonip.moviereviewsapp.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//This is ReviewsService interface we written GET call for NewYork_Movie_Reviews API
interface ReviewsService {
    @GET("reviews/search.json")
    fun list(@Query("api-key") apiKey: String = API_KEY) : Call<ReviewsResult>
}