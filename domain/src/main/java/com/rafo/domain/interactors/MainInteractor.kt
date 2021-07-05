package com.rafo.domain.interactors

import com.rafo.entities.Result
import com.rafo.entities.responseentity.Content

interface MainInteractor {
    suspend fun getPopularFilms(page: Int): Result<Content>
}