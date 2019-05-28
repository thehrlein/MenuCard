package com.tobiapplications.menu.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by tobias.hehrlein on 20.01.2019.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(gson: Gson, httpLoggingInterceptor: HttpLoggingInterceptor,
//                       okHttpClient: OkHttpClient) : Retrofit {
//
//        val timeOut = 30L
//        val clientBuilder = okHttpClient.newBuilder()
//        clientBuilder.readTimeout(timeOut, TimeUnit.SECONDS)
//        clientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS)
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        clientBuilder.addInterceptor(httpLoggingInterceptor)
//
//        return Retrofit.Builder().client(clientBuilder.build())
//            .baseUrl(TimelineUrls.FACEBOOK_GRAPH_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit) : TimelineApi {
//        return retrofit.create(TimelineApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideNetworkManager(api: TimelineApi) : NetworkManager {
//        return NetworkManager(api)
//    }
}

