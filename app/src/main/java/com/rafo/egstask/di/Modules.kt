package com.rafo.egstask.di

import com.rafo.egstask.detail.viewmodel.DetailViewModel
import com.rafo.egstask.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}