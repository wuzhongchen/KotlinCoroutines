package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dongnaoedu.kotlincoroutine.R
import kotlin.coroutines.*

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity05 : AppCompatActivity() {


    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //协程体
        val continuation = suspend {
            5
        }.createCoroutine(object : Continuation<Int>{
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<Int>) {
                println("Coroutine End: $result")
            }
        })
        continuation.resume(Unit)

    }


}