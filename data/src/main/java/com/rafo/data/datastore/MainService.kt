package com.rafo.data.datastore

import com.rafo.entities.Result
import com.rafo.entities.responseentity.Content

interface MainService {
    suspend fun getPopularFilms(page: Int): Result<Content>
}