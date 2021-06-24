package com.example.robotdeliveryman.model


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class InputValidatorTest{

    private val inputValidator = InputValidator()

    @Test
    fun testInputValidator1(){
        val isValid = inputValidator.checkInput("")
        assertThat(isValid).isFalse()
    }

    @Test
    fun testInputValidator2(){
        val isValid = inputValidator.checkInput("3x3 (1, 3))(")
        assertThat(isValid).isFalse()
    }

    @Test
    fun testInputValidator3(){
        val isValid = inputValidator.checkInput("5x6 (1, 33)")
        assertThat(isValid).isFalse()
    }

    @Test
    fun testInputValidator4(){
        val isValid = inputValidator.checkInput("5x6 1, 3) (2, 4) (5, 3)")
        assertThat(isValid).isFalse()
    }

    @Test
    fun testInputValidator5(){
        val isValid = inputValidator.checkInput("4x4 (1, 3) (2, 4) (3, 4)")
        assertThat(isValid).isTrue()
    }

}