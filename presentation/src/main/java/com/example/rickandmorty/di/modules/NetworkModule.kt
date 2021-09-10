package com.example.rickandmorty.di.modules

import com.example.data.network.PersonApi
import com.example.rickandmorty.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providePersonApi(retrofit: Retrofit) = retrofit.create(PersonApi::class.java)

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideLogger() = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY.takeIf { BuildConfig.DEBUG }
            ?: HttpLoggingInterceptor.Level.NONE
    )

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}