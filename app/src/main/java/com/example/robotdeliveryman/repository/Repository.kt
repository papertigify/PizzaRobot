package com.example.robotdeliveryman.repository

import com.example.robotdeliveryman.model.InputValidator
import com.example.robotdeliveryman.model.RouteGenerator
import com.example.robotdeliveryman.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Repository(private val inputValidator: InputValidator, private val routeGenerator: RouteGenerator) {

    suspend fun getRoute(inputString: String, shouldDropMultiplePizza: Boolean): Resource<String> {
        return withContext(Dispatchers.IO) {
            // little delay to simulate long background work
            delay(300L)
            val isInputValid = inputValidator.checkInput(inputString)
            return@withContext if (!isInputValid) {
                Resource.error("An error occured, please, check input", null)
            } else {
                val result = routeGenerator.generateOutput(inputString, shouldDropMultiplePizza)
                Resource.success(result)
            }
        }
    }
}