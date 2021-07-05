package com.rafo.data.datastore

import com.rafo.entities.Result
import com.rafo.entities.responseentity.MovieDetail

interface DetailService {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>
}