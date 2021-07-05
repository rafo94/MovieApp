package com.rafo.domain.di

import com.rafo.domain.interactors.DetailInteractor
import com.rafo.domain.interactors.MainInteractor
import com.rafo.domain.usecases.DetailUseCase
import com.rafo.domain.usecases.MainUseCase
import org.koin.dsl.module

val interactorsModule = module {
    factory<MainInteractor> { MainUseCase(get()) }
    factory<DetailInteractor> { DetailUseCase(get()) }
}