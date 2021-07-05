package com.rafo.domain.interactors

import com.rafo.entities.Result
import com.rafo.entities.responseentity.MovieDetail

interface DetailInteractor {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>
}