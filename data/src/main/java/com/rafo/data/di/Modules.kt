package com.rafo.data.di

import com.rafo.data.BuildConfig.BASE_URL
import com.rafo.data.dataservice.RetrofitService
import com.rafo.data.datastore.DetailService
import com.rafo.data.datastore.MainService
import com.rafo.data.repository.DetailServiceImpl
import com.rafo.data.repository.MainServiceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .apply {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }).build()
                )
            }
            .build()
    }
    single<RetrofitService> { get<Retrofit>().create(RetrofitService::class.java) }
}

val repositoryModule = module {
    single<MainService> { MainServiceImpl(get()) }
    single<DetailService> { DetailServiceImpl(get()) }
}