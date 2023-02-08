package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dongnaoedu.kotlincoroutine.R
import com.dongnaoedu.kotlincoroutine.model.User
import com.dongnaoedu.kotlincoroutine.api.userServiceApi

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity01 : AppCompatActivity() {

    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "Jack"

        val nameTextView2 = findViewById<TextView>(R.id.nameTextView2)

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                object : AsyncTask<Void,Void, User>(){
                    override fun doInBackground(vararg params: Void?): User? {
                        return userServiceApi.loadUser("13859").execute().body()
                    }
                    override fun onPostExecute(user: User?) {
                        nameTextView.text = "title:${user?.catename}"
                        nameTextView2.text = "intro:${user?.intro}"
                    }
                }.execute()
            }
        }
    }

}