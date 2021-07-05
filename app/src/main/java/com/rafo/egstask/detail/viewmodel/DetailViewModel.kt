package com.rafo.egstask.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafo.domain.interactors.DetailInteractor
import com.rafo.egstask.util.publishFlow
import com.rafo.entities.Result
import com.rafo.entities.responseentity.Content
import com.rafo.entities.responseentity.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val detailInteractor: DetailInteractor) : ViewModel() {

    private val _movieDetailData: MutableSharedFlow<MovieDetail> = publishFlow()
    val movieDetail: SharedFlow<MovieDetail> = _movieDetailData.asSharedFlow()

    private val _movieDetailError: MutableSharedFlow<String> = publishFlow()
    val movieDetailError: SharedFlow<String> = _movieDetailError.asSharedFlow()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val data = detailInteractor.getMovieDetail(movieId)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    data.data?.let {
                        _movieDetailData.emit(it)
                    }
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    _movieDetailError.emit(data.errors.errorMessage ?: "")
                }
            }
        }
    }
}