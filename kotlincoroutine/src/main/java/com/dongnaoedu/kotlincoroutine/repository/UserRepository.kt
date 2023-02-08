package com.dongnaoedu.kotlincoroutine.repository

import com.dongnaoedu.kotlincoroutine.model.User
import com.dongnaoedu.kotlincoroutine.api.userServiceApi

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class UserRepository {

    suspend fun getUser(bid: String): User {
        return userServiceApi.getUser(bid)
    }

}