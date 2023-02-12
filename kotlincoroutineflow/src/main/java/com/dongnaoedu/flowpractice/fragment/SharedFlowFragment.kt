package com.dongnaoedu.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dongnaoedu.flowpractice.R
import com.dongnaoedu.flowpractice.databinding.FragmentSharedFlowBinding
import com.dongnaoedu.flowpractice.databinding.FragmentUserBinding
import com.dongnaoedu.flowpractice.viewmodel.SharedFlowViewModel
import com.dongnaoedu.flowpractice.viewmodel.UserViewModel


class SharedFlowFragment : Fragment() {

    private val viewModel by viewModels<SharedFlowViewModel>()

    private val mBinding: FragmentSharedFlowBinding by lazy {
        FragmentSharedFlowBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.apply {
            btnStart.setOnClickListener {
                viewModel.startRefresh()
            }

            btnStop.setOnClickListener {
                viewModel.stopRefresh()
            }
        }
    }

}