package com.dongnaoedu.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class NumberViewModel : ViewModel() {

    val number = MutableStateFlow(0)

    fun increment() {
        number.value++
    }

    fun decrement() {
        number.value--
    }
}