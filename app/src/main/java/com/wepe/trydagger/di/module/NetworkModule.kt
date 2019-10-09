package com.wepe.trydagger.di.module

import android.app.Application
import android.os.SystemClock
import android.util.Log
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.MainApplication
import com.wepe.trydagger.data.network.ApiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
        val httpInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("Movie", it)
        })

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val interceptor = Interceptor { chain ->
            SystemClock.sleep(1000)
            chain.proceed(chain.request())
        }

        httpInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder().addInterceptor(httpInterceptor)
            .addNetworkInterceptor(interceptor)
            .dispatcher(dispatcher)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestClient(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}