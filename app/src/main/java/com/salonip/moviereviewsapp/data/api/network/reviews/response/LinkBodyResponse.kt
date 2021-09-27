package com.salonip.moviereviewsapp.data.api.network.reviews.response

import com.squareup.moshi.Json
//This is model contains link details
data class LinkBodyResponse(
    @field:Json(name = "suggested_link_text") val linkDescription: String,
    @field:Json(name = "url") val url: String
)