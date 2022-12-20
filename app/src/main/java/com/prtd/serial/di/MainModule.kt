package com.prtd.serial.di

import com.prtd.serial.common.Constants.Base_Url
import com.prtd.serial.data.remote.RestApiService
import com.prtd.serial.data.remote.repository.RestApiImpl
import com.prtd.serial.domain.repository.RestApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRestApiService(retrofit: Retrofit): RestApiService{
        return retrofit.create(RestApiService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepo(restApiService: RestApiService) :RestApiRepo{
        return RestApiImpl(restApiService)
    }
}