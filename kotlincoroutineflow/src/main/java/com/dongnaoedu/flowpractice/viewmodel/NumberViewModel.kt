package com.dongnaoedu.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class NumberViewModel : ViewModel() {

    //需要一个初始值 支持变换 合并等操作符
    val number = MutableStateFlow(0)
    //StateFlow和LiveData
    fun increment() {
        number.value++
    }

    fun decrement() {
        number.value--
    }
}