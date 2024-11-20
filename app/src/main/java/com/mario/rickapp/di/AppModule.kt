package com.mario.rickapp.di

import com.mario.rickapp.data.remote.RickApi
import com.mario.rickapp.data.repository.RickRepositoryImpl
import com.mario.rickapp.domain.repository.RickRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideRickApi(): RickApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: RickApi): RickRepository {
        return RickRepositoryImpl(api)
    }
}