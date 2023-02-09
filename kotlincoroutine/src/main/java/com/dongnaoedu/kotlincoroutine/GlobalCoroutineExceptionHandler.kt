package com.dongnaoedu.kotlincoroutine

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 *
 * @author ningchuanqi
 * @version V1.0
 * 使用全局异常捕捉仍然会崩溃 但是可以定位发现问题
 */
class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {

    override val key = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.d("ningli","Unhandled Coroutine Exception: $exception")
    }
}