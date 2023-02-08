package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dongnaoedu.kotlincoroutine.R
import com.dongnaoedu.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.*
import kotlin.coroutines.*

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity06 : AppCompatActivity(), CoroutineScope by MainScope() {

    //private val mainScope = MainScope()
    private var nameTextView:TextView? = null

    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView?.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                /*mainScope.launch {
                    val user = userServiceApi.getUser("xx")
                    nameTextView?.text = "address:${user?.address}"
                    *//*try {
                        delay(10000)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }*//*
                }*/
                launch {
                    val user = userServiceApi.getUser("xx")
                    nameTextView?.text = "address:${user?.address}"
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //mainScope.cancel()
        cancel()
    }


}