package com.dongnaoedu.flowpractice.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
object RetrofitClient {

    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build())

            .baseUrl("http://androidmcdn.goreadnovels.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleApi: ArticleApi by lazy {
        instance.create(ArticleApi::class.java)
    }
}