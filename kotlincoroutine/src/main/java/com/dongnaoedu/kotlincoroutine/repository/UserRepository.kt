package com.dongnaoedu.kotlincoroutine.repository

import com.dongnaoedu.kotlincoroutine.api.User
import com.dongnaoedu.kotlincoroutine.api.userServiceApi

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class UserRepository {

    suspend fun getUser(name: String): User {
        return userServiceApi.getUser(name)
    }

}