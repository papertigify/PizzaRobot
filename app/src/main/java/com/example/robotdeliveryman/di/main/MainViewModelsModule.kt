package com.example.robotdeliveryman.di.main

import androidx.lifecycle.ViewModel
import com.example.robotdeliveryman.di.ViewModelKey
import com.example.robotdeliveryman.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}