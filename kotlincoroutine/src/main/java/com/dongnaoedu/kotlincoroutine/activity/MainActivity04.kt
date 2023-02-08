package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dongnaoedu.kotlincoroutine.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity04 : AppCompatActivity() {

    private var nameTextView:TextView? = null
    private var nameTextView2:TextView? = null
    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView2 = findViewById<TextView>(R.id.nameTextView2)
        nameTextView?.text = "activity04"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    //挂起
                    delay(12000)
                    Log.d("jason", "${Thread.currentThread().name}:after delay.")
                }

                //阻塞
//                Thread.sleep(12000)
//                Log.d("jason", "${Thread.currentThread().name}:after sleep.")
            }
        }
    }


}