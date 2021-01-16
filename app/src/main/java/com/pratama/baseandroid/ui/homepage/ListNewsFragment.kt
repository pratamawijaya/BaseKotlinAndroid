package com.pratama.baseandroid.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.databinding.FragmentListNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNewsFragment : BaseFragmentBinding<FragmentListNewsBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListNewsFragment().apply {
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListNewsBinding =
        FragmentListNewsBinding::inflate

    override fun setupView() {
        // todo
    }

}