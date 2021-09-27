package com.salonip.moviereviewsapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.salonip.moviereviewsapp.data.api.mapper.ReviewMapper
import com.salonip.moviereviewsapp.data.api.network.RetrofitInitializer
import com.salonip.moviereviewsapp.data.api.network.reviews.response.ReviewsResult
import com.salonip.moviereviewsapp.data.domain.Review
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSourceRepository(
    private val liveData: MutableLiveData<ArrayList<Review>>
) : Callback<ReviewsResult> {

    fun callRetrofitApi() =
        RetrofitInitializer().reviewService().list().enqueue(this)

    override fun onResponse(
        call: Call<ReviewsResult>,
        response: Response<ReviewsResult>
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                // Set transformed Review data to livedata if response successful
                liveData.value = ReviewMapper.responseToDomain(it.results)
            }
        }
    }

    override fun onFailure(
        call: Call<ReviewsResult>,
        throwable: Throwable
    ) {
        liveData.value = null
    }

}