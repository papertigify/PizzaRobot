package com.example.robotdeliveryman.di

import androidx.lifecycle.ViewModelProvider
import com.example.robotdeliveryman.ui.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}