package com.rafo.data.repository

import com.rafo.data.dataservice.RetrofitService
import com.rafo.data.datastore.DetailService
import com.rafo.data.util.analyzeResponse
import com.rafo.data.util.makeApiCall
import com.rafo.entities.Result
import com.rafo.entities.responseentity.MovieDetail

class DetailServiceImpl(private val retrofitService: RetrofitService) : DetailService {

    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail> =
        makeApiCall({ analyzeResponse(retrofitService.getMovieDetail(movieId = movieId)) })
}