package com.dongnaoedu.flowpractice.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dongnaoedu.flowpractice.R
import com.dongnaoedu.flowpractice.databinding.FragmentDownloadBinding
import com.dongnaoedu.flowpractice.databinding.FragmentHomeBinding
import com.dongnaoedu.flowpractice.download.DownloadManager
import com.dongnaoedu.flowpractice.download.DownloadStatus
import kotlinx.coroutines.flow.collect
import java.io.File


class DownloadFragment : Fragment() {

    val URL = "http://192.168.1.4:8080/kotlinstudyserver/pic.JPG"

    private val mBinding: FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            context?.apply {
                val file = File(getExternalFilesDir(null)?.path, "pic.JPG")
                DownloadManager.download(URL, file).collect { status ->
                    when (status) {
                        is DownloadStatus.Progress -> {
                            mBinding.apply {
                                progressBar.progress = status.value
                                tvProgress.text = "${status.value}%"
                            }
                        }
                        is DownloadStatus.Error -> {
                            Toast.makeText(context, "下载错误", Toast.LENGTH_SHORT).show()
                        }
                        is DownloadStatus.Done -> {
                            mBinding.apply {
                                progressBar.progress = 100
                                tvProgress.text = "100%"
                            }
                            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Log.d("ning", "下载失败.")
                        }
                    }
                }
            }
        }

    }

}