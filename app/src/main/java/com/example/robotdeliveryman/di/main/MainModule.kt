package com.example.robotdeliveryman.di.main

import com.example.robotdeliveryman.model.InputValidator
import com.example.robotdeliveryman.model.RouteGenerator
import com.example.robotdeliveryman.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    companion object {

        @MainScope
        @Provides
        fun provideRepository(inputValidator: InputValidator,
        routeGenerator: RouteGenerator)
        : Repository{
            return Repository(inputValidator, routeGenerator)
        }

        @MainScope
        @Provides
        fun provideInputValidator(): InputValidator{
            return InputValidator()
        }

        @MainScope
        @Provides
        fun provideRouteGenerator(): RouteGenerator{
            return RouteGenerator()
        }
    }
}