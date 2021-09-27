package com.salonip.moviereviewsapp.data.api.network.reviews.response

import com.squareup.moshi.Json

// This model contains Multimedia fields
data class MultimediaBodyResponse(
    @field:Json(name = "type") val type: String,
    @field:Json(name = "src") val src: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "width") val width: Int
)