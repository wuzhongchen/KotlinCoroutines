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

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity06 : AppCompatActivity(), CoroutineScope by MainScope() {

    //工厂模式
//    private val mainScope = MainScope()
    private var nameTextView:TextView? = null
    private var nameTextView2:TextView? = null
    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView?.text = "Jack"
        nameTextView2 = findViewById<TextView>(R.id.nameTextView2)
        nameTextView?.text = "activity04"

        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("ningli", "Caught $exception")
        }

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
//                mainScope.launch {
//                    //retrofit可以自动侦测到 如果是挂起函数 就会在io线程处理
//                    val user = userServiceApi.getUser("xx")
//                    nameTextView?.text = "title:${user?.catename}"
//                    try {
//                        delay(10000)
//                    }catch (e:Exception){
//                        e.printStackTrace()
//                    }
//                }

                launch() {
                    Log.d("ningli", "on Click.")
                    "abc".substring(10)
                }
//                launch {
//                    val user = userServiceApi.getUser("xx")
//                    nameTextView?.text = "title:${user?.catename}"
//                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消协程
//        mainScope.cancel()
        cancel()
    }


}