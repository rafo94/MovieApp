package com.rafo.egstask.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafo.domain.interactors.MainInteractor
import com.rafo.egstask.util.publishFlow
import com.rafo.entities.Result
import com.rafo.entities.responseentity.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val mainInteractor: MainInteractor) : ViewModel() {

    private val _movieListData: MutableSharedFlow<Content> = publishFlow()
    val movieList: SharedFlow<Content> = _movieListData.asSharedFlow()

    private val _movieListError: MutableSharedFlow<String> = publishFlow()
    val movieListError: SharedFlow<String> = _movieListError.asSharedFlow()

    fun getPopularMovieList(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val data = mainInteractor.getPopularFilms(page)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    data.data?.let {
                        _movieListData.emit(it)
                    }
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    _movieListError.emit(data.errors.errorMessage ?: "")
                }
            }
        }
    }
}