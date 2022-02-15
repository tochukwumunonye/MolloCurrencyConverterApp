package com.tochukwu.mollocurrencyconverterapp.di

import com.tochukwu.mollocurrencyconverterapp.data.CurrencyApi
import com.tochukwu.mollocurrencyconverterapp.repository.DefaultMainRepository
import com.tochukwu.mollocurrencyconverterapp.repository.MainRepository
import com.tochukwu.mollocurrencyconverterapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
//Dependency Injection was used to provide classes with their dependencies following the solid principle

private const val BASE_URL = "http://data.fixer.io/api/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)


    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyApi): MainRepository =  DefaultMainRepository(api)



    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object  : DispatcherProvider{

        override val main: CoroutineDispatcher
        get() = Dispatchers.Main

        override val io: CoroutineDispatcher
        get() = Dispatchers.IO

        override val default: CoroutineDispatcher
        get() = Dispatchers.Default

        override val unconfined : CoroutineDispatcher
        get() = Dispatchers.Unconfined
    }

}






