package com.soten.tablepay.di

import com.soten.tablepay.BuildConfig
import com.soten.tablepay.data.api.TablePayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideTablePayApi() = Retrofit.Builder()
        .baseUrl(BuildConfig.TABLE_PAY_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TablePayService::class.java)

}