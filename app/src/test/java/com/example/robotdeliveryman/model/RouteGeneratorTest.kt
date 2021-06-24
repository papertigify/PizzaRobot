package com.example.robotdeliveryman.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RouteGeneratorTest{

    private val routeGenerator = RouteGenerator()

    @Test
    fun testRouteGenerator1(){
        val result = routeGenerator.generateOutput("5x5 (1, 3) (4, 4)", false)
        assertThat(result).isEqualTo("ENNNDEEEND")
    }

    @Test
    fun testRouteGenerator2(){
        val result = routeGenerator.generateOutput(
            "5x5 (0, 0) (1, 3) (4,4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
            , false)
        assertThat(result).isEqualTo("NDENNDEDESDEDSDNNND")
    }

    @Test
    fun testRouteGenerator3(){
        val result = routeGenerator.generateOutput(
            "5x5 (0, 0) (1, 3) (4,4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
            , true)
        assertThat(result).isEqualTo("NDENNDEDESDEDDSDNNND")
    }

    @Test
    fun testRouteGenerator4(){
        val result = routeGenerator.generateOutput("4x4 (1, 1) (2, 3) (4, 0)", false)
        assertThat(result).isEqualTo("ENDENNDEESSSD")
    }

    @Test
    fun testRouteGenerator5(){
        val result = routeGenerator.generateOutput("5x4 (3, 1) (2, 3) (5, 4)", false)
        assertThat(result).isEqualTo("EEENDWNNDEEEND")
    }
}