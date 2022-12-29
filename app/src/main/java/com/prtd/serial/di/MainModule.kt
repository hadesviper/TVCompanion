package com.prtd.serial.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.prtd.serial.common.Constants.Base_Url
import com.prtd.serial.data.local.WatchLaterDatabase
import com.prtd.serial.data.local.repository.WatchLaterImpl
import com.prtd.serial.data.remote.RestApiService
import com.prtd.serial.data.remote.repository.RestApiImpl
import com.prtd.serial.domain.repository.RestApiRepo
import com.prtd.serial.domain.repository.WatchLaterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun getRestApiService(retrofit: Retrofit): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepo(restApiService: RestApiService): RestApiRepo {
        return RestApiImpl(restApiService)
    }

    @Provides
    @Singleton
    fun provideWatchLaterDatabase(app: Application): WatchLaterDatabase {
        return Room.databaseBuilder(
            app, WatchLaterDatabase::class.java, WatchLaterDatabase.DB_Name
        )
            .build()
    }

    @Singleton
    @Provides
    fun getWatchLaterRepo(db: WatchLaterDatabase): WatchLaterRepo {
        return WatchLaterImpl(db.watchLaterDao())
    }

    @Singleton
    @Provides
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }
}