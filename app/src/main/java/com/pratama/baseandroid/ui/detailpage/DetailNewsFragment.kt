package com.pratama.baseandroid.ui.detailpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.databinding.FragmentDetailNewsBinding
import com.pratama.baseandroid.ui.dto.NewsDto

class DetailNewsFragment : BaseFragmentBinding<FragmentDetailNewsBinding>() {

    private val args: DetailNewsFragmentArgs by navArgs()

    private var newsDto: NewsDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsDto = args.newsDto
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailNewsBinding
        get() = FragmentDetailNewsBinding::inflate

    override fun setupView(binding: FragmentDetailNewsBinding) {
        with(binding) {
            newsDto?.let {
                newsTitle.text = it.title
            }
        }
    }
}