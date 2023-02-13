package com.dongnaoedu.flowpractice.net

import com.dongnaoedu.flowpractice.model.SearchEntity
import com.dongnaoedu.flowpractice.model.SearchResultEntity
import retrofit2.http.*

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
interface ArticleApi {

//    @GET("article")
//    suspend fun searchArticles(
//        @Query("key") key: String
//    ): List<Article>

    @FormUrlEncoded
    @POST("getbooks")
    suspend fun searchArticles(
        @Field("keyword") key: String): SearchResultEntity

}