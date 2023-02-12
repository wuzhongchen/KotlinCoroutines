package com.dongnaoedu.flowpractice.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
object RetrofitClient {

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("http://192.168.1.4:8080/kotlinstudyserver/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleApi: ArticleApi by lazy {
        instance.create(ArticleApi::class.java)
    }
}