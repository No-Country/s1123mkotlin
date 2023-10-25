package com.nocountry.s1123mkotlin.di

import dagger.Module
import dagger.Provides
import com.nocuntry.medichild.viewmodel.IAViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideIAViewModel(): IAViewModel {
        return IAViewModel()
    }
}
