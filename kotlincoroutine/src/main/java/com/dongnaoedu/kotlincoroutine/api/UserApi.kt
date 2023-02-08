package com.dongnaoedu.kotlincoroutine.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
data class User(val name: String, val address: String)

val userServiceApi: UserServiceApi by lazy {
    val retrofit = retrofit2.Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor {
                it.proceed(it.request()).apply {
                    Log.d("jason", "request:${code()}")
                    //Log.d("jason", "boy:${body()?.string()}")
                }
            }.build())
            .baseUrl("http://192.168.1.4:8080/kotlinstudyserver/")
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

    @GET("user")
    fun loadUser(@Query("name") name: String) : Call<User>

    @GET("user")
    suspend fun getUser(@Query("name") name: String) : User

}