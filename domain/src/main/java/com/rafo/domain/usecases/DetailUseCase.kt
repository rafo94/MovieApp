package com.rafo.domain.usecases

import com.rafo.data.datastore.DetailService
import com.rafo.domain.interactors.DetailInteractor
import com.rafo.entities.Result
import com.rafo.entities.responseentity.MovieDetail

class DetailUseCase(private val detailService: DetailService) : DetailInteractor {
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail> =
        detailService.getMovieDetail(movieId)
}