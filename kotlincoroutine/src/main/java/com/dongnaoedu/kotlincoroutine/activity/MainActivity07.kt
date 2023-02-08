package com.dongnaoedu.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dongnaoedu.kotlincoroutine.R
import com.dongnaoedu.kotlincoroutine.databinding.ActivityMainBinding
import com.dongnaoedu.kotlincoroutine.viewmodel.MainViewModel

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class MainActivity07 : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("StaticFieldLeak","SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        binding.submitButton.setOnClickListener {
            mainViewModel.getUser("13859")
        }
    }

}