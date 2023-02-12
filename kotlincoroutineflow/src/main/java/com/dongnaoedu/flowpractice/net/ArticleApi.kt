package com.dongnaoedu.flowpractice.net

import com.dongnaoedu.flowpractice.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
interface ArticleApi {

    @GET("article")
    suspend fun searchArticles(
        @Query("key") key: String
    ): List<Article>

}