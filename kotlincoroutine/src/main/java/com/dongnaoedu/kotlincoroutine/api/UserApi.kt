package com.dongnaoedu.kotlincoroutine.api

import android.util.Log
import com.dongnaoedu.kotlincoroutine.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
//data class User(val name: String, val address: String)

private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 10L

val userServiceApi: UserServiceApi by lazy {
    val retrofit = retrofit2.Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor {
                it.proceed(it.request()).apply {
                    Log.d("jason", "request:${code()}")
                    //Log.d("jason", "boy:${body()?.string()}")
                }
            }.build())
            .baseUrl("http://androidmcdn.goreadnovels.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    retrofit.create(UserServiceApi::class.java)

//    val rtrofit2 = retrofit2.Retrofit.Builder()
//        .client(OkHttpClient.Builder().addInterceptor({
//
//        }).build())
//        .baseUrl("http://192.168.1.4:8080/kotlinstudyserver/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//    rtrofit2.create(UserServiceApi::class.java)
}


interface UserServiceApi {

    @FormUrlEncoded
    @POST("clientGetBookInfo")
    fun loadUser(@Field("bid") bid: String) : Call<User>

    //异步 挂起
    @FormUrlEncoded
    @POST("clientGetBookInfo")
    suspend fun getUser(@Field("bid") bid: String) : User

}