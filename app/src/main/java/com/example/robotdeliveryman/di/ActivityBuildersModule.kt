package com.example.robotdeliveryman.di

import com.example.robotdeliveryman.ui.MainActivity
import com.example.robotdeliveryman.di.main.MainModule
import com.example.robotdeliveryman.di.main.MainScope
import com.example.robotdeliveryman.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}