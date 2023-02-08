package com.dongnaoedu.kotlincoroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongnaoedu.kotlincoroutine.model.User
import com.dongnaoedu.kotlincoroutine.repository.UserRepository
import kotlinx.coroutines.launch

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainViewModel() : ViewModel() {

    val userLiveData = MutableLiveData<User>()

    private val userRepository = UserRepository()

    fun getUser(bid: String) {
        viewModelScope.launch {
            userLiveData.value = userRepository.getUser(bid)
        }
    }

}