package com.rafo.data.repository

import com.rafo.data.dataservice.RetrofitService
import com.rafo.data.datastore.MainService
import com.rafo.data.util.analyzeResponse
import com.rafo.data.util.makeApiCall
import com.rafo.entities.Result
import com.rafo.entities.responseentity.Content

class MainServiceImpl(private val retrofitService: RetrofitService) : MainService {

    override suspend fun getPopularFilms(page: Int): Result<Content> =
        makeApiCall({ analyzeResponse(retrofitService.getPopularMovies(page = page)) })
}