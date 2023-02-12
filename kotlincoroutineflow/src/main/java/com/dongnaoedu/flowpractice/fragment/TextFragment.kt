package com.dongnaoedu.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dongnaoedu.flowpractice.R
import com.dongnaoedu.flowpractice.common.LocalEventBus
import com.dongnaoedu.flowpractice.databinding.FragmentTextBinding
import com.dongnaoedu.flowpractice.databinding.FragmentUserBinding
import com.dongnaoedu.flowpractice.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect


class TextFragment : Fragment() {


    private val mBinding: FragmentTextBinding by lazy {
        FragmentTextBinding.inflate(layoutInflater)
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
            LocalEventBus.events.collect {
                mBinding.tvTime.text = it.timestamp.toString()
            }
        }
    }

}