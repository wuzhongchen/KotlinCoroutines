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

    //离线包地址
    val URL = "http://img1.suiyuexiaoshuo.com/mcdn/offline_pack/android/pack/offline_v1000029.zip?ver=1000029&t=1675913807";

    val URL2 = "https://r1---sn-i3b7knzs.c.2mdn.net/videoplayback/id/6edccd1852eccfc1/itag/347/source/web_video_ads/ctier/L/acao/yes/ip/0.0.0.0/ipbits/0/expire/1707460925/sparams/acao,ctier,expire,id,ip,ipbits,ipbypass,itag,mh,mip,mm,mn,ms,mv,mvi,pl,source/signature/CA441504C9AE75CCA71574D9C55A994C43671D.3BFB5BCC9BE2EB85605107CD513CD4478BF2C90B/key/cms1/mh/gL/pl/36/redirect_counter/1/rm/sn-i3bdk7e/req_id/944d5f79289a3ee/cms_redirect/yes/ipbypass/yes/mip/2407:cdc0:aa4a:2cfe:5c62:ee73:6bbf:bfe2/mm/42/mn/sn-i3b7knzs/ms/onc/mt/1675924612/mv/m/mvi/1?file=file.mp4";

    val URL3 = "https://r1---sn-i3b7knzs.c.2mdn.net/videoplayback/id/6edccd1852eccfc1/itag/347/source/web_video_ads/ctier/L/acao/yes/ip/0.0.0.0/ipbits/0/expire/1707460925/sparams/acao,ctier,expire,id,ip,ipbits,ipbypass,itag,mh,mip,mm,mn,ms,mv,mvi,pl,source/signature/CA441504C9AE75CCA71574D9C55A994C43671D.3BFB5BCC9BE2EB85605107CD513CD4478BF2C90B/key/cms1/mh/gL/pl/36/redirect_counter/1/rm/sn-i3bdk7e/req_id/944d5f79289a3ee/cms_redirect/yes/ipbypass/yes/mip/2560:2cfe:cdc0:aa4a:5c62:bfe2:ee73:6bbf/mm/42/mn/sn-i3b7knzs/ms/onc/mt/1675924612/mv/m/mvi/1?file=file.mp4";

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
                val file = File(getExternalFilesDir(null)?.path, "offline.zip")
//                val file = File(getExternalFilesDir(null)?.path, "video.mp4")
                DownloadManager.download(URL, file).collect { status ->
                    when (status) {
                        is DownloadStatus.Progress -> {
                            mBinding.apply {
                                progressBar.progress = status.value
                                tvProgress.text = "${status.value}%"
                            }
                        }
                        is DownloadStatus.Error -> {
                            Log.d("ningli", "下载错误$status")
                        }
                        is DownloadStatus.Done -> {
                            mBinding.apply {
                                progressBar.progress = 100
                                tvProgress.text = "100%"
                            }
                            Log.d("ningli", "下载完成")
                        }
                        else -> {
                            Log.d("ningli", "下载失败.")
                        }
                    }
                }
            }
        }

    }

}