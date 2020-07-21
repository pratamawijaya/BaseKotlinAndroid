package com.pratama.baseandroid.ui.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pratama.baseandroid.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomePageActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        homeViewModel.getTopHeadlinesByCountry(
            country = "us",
            category = "business"
        )
    }
}