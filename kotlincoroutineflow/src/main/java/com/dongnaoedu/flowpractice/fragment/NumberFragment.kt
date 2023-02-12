package com.dongnaoedu.flowpractice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dongnaoedu.flowpractice.R
import com.dongnaoedu.flowpractice.databinding.FragmentArticleBinding
import com.dongnaoedu.flowpractice.databinding.FragmentNumberBinding
import com.dongnaoedu.flowpractice.viewmodel.ArticleViewModel
import com.dongnaoedu.flowpractice.viewmodel.NumberViewModel
import kotlinx.coroutines.flow.collect


class NumberFragment : Fragment() {
    private val viewModel by viewModels<NumberViewModel>()

    private val mBinding: FragmentNumberBinding by lazy {
        FragmentNumberBinding.inflate(layoutInflater)
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
            btnPlus.setOnClickListener {
                viewModel.increment()
            }

            btnMinus.setOnClickListener {
                viewModel.decrement()
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.number.collect { value ->
                mBinding.tvNumber.text = "$value"
            }
        }
    }
}