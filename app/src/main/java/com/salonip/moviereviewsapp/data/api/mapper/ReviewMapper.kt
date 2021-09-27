package com.salonip.moviereviewsapp.data.api.mapper

import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.data.api.network.reviews.response.ReviewBodyResponse

// This class is used for Mapping ReviewBodyResponse model to Review model
class ReviewMapper {
    companion object {
        fun responseToDomain(listReviewBodyResponse: List<ReviewBodyResponse>): ArrayList<Review> {
            val listReview = ArrayList<Review>()

            for (reviewResponse in listReviewBodyResponse) {
                val review = Review(
                    reviewResponse.title,
                    reviewResponse.author,
                    reviewResponse.headline,
                    reviewResponse.summary,
                    reviewResponse.publicationDate,
                    reviewResponse.linkBody.url,
                    reviewResponse.linkBody.linkDescription,
                    reviewResponse.multimedia.src
                )
                listReview.add(review)
            }
            return listReview
        }
    }
}