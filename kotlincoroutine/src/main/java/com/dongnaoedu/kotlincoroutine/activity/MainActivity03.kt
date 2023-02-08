package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dongnaoedu.kotlincoroutine.R
import com.dongnaoedu.kotlincoroutine.model.User
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
class MainActivity03 : AppCompatActivity() {

    private var nameTextView:TextView? = null
    private var nameTextView2:TextView? = null

    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView2 = findViewById<TextView>(R.id.nameTextView2)
        nameTextView?.text = "activity03"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    getUser()
                }
            }
        }
    }

    private suspend fun getUser(){
        val user = get()
        show(user)
    }

    private suspend fun get() = withContext(Dispatchers.IO){
        userServiceApi.getUser("13859")
    }

    private fun show(user: User) {
        nameTextView?.text = "title:${user?.catename}"
        nameTextView2?.text = "intro:${user?.intro}"
    }

}