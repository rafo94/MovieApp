package com.rafo.domain.usecases

import com.rafo.data.datastore.MainService
import com.rafo.domain.interactors.MainInteractor
import com.rafo.entities.Result
import com.rafo.entities.responseentity.Content

class MainUseCase(private val mainService: MainService) : MainInteractor {
    override suspend fun getPopularFilms(page: Int): Result<Content> =
        mainService.getPopularFilms(page)
}