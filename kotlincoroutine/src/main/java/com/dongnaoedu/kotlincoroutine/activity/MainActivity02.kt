package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dongnaoedu.kotlincoroutine.R
import com.dongnaoedu.kotlincoroutine.api.User
import com.dongnaoedu.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity02 : AppCompatActivity() {

    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val user = withContext(Dispatchers.IO){
                        userServiceApi.getUser("xxx")
                    }
                    nameTextView.text = "address:${user?.address}"
                }
            }
        }
    }

}