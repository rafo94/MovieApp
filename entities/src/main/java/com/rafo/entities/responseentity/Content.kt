package com.rafo.entities.responseentity

import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Results>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)