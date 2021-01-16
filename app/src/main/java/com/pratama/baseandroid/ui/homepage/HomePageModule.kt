package com.pratama.baseandroid.ui.homepage

import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.domain.usecase.GetTopHeadlineUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class HomePageModule {

    @Provides
    fun provideTopHeadLineUseCase(repository: NewsRepository): GetTopHeadlineUseCase {
        return GetTopHeadlineUseCase(repository)
    }

    @Provides
    fun provideListNewsViewModel(useCase: GetTopHeadlineUseCase): ListNewsViewModel {
        return ListNewsViewModel(useCase)
    }
}