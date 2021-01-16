package com.pratama.baseandroid.ui.detailpage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.databinding.FragmentDetailNewsBinding

class DetailNewsFragment : BaseFragmentBinding<FragmentDetailNewsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailNewsBinding
        get() = FragmentDetailNewsBinding::inflate

    override fun setupView() {
        // todo

    }
}