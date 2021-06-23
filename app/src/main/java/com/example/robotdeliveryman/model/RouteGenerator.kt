package com.example.robotdeliveryman.model

import com.example.robotdeliveryman.utils.Constants.Companion.BASE_DESTINATION
import com.example.robotdeliveryman.utils.Constants.Companion.DOWN
import com.example.robotdeliveryman.utils.Constants.Companion.DROP
import com.example.robotdeliveryman.utils.Constants.Companion.LEFT
import com.example.robotdeliveryman.utils.Constants.Companion.RIGHT
import com.example.robotdeliveryman.utils.Constants.Companion.UP
import kotlin.math.pow
import kotlin.math.sqrt

// short description of the algorithm:
// The algorithm takes the starting coordinate, finds the point closest to it, builds a path to it,
// then repeats the same thing with new point.
// This continues until all points are passed.
class RouteGenerator {

    fun generateOutput(inputString: String, shouldDropMultiplePizza: Boolean): String{
        val list = getPairsFromString(inputString)
        val localList = list.toMutableList()
        var output = ""
        var currentElement = BASE_DESTINATION

        while (localList.isNotEmpty()){
            val nearest = findNearest(currentElement, localList)
            output += generateWayToNearest(currentElement, nearest, shouldDropMultiplePizza)

            currentElement = nearest
            localList.remove(nearest)
        }
        return output
    }

    private fun generateWayToNearest
                (start: Pair<Int, Int>,
                 end: Pair<Int, Int>,
                 shouldDropMultiplePizza: Boolean): String{
        val up = UP
        val down = DOWN
        val right = RIGHT
        val left = LEFT
        val drop = DROP
        // the relative position of points in space can be divided into 4 main cases,
        // and 4 degenerate cases can also be distinguished
        return when {
            (start.first > end.first) &&
                    (start.second > end.second) -> {
                val dx = start.first - end.first
                val dy = start.second - end.second
                left.repeat(dx) + down.repeat(dy) + drop
            }
            (start.first == end.first) &&
                    (start.second > end.second) -> {
                val dx = 0
                val dy = start.second - end.second
                down.repeat(dy) + drop
            }
            (start.first < end.first) &&
                    (start.second > end.second) -> {
                val dx = end.first - start.first
                val dy = start.second - end.second
                right.repeat(dx) + down.repeat(dy) + drop
            }
            (start.first < end.first) &&
                    (start.second == end.second) -> {
                val dx = end.first - start.first
                val dy = 0
                right.repeat(dx) + drop
            }
            (start.first < end.first) &&
                    (start.second < end.second) -> {
                val dx = end.first - start.first
                val dy = end.second -start.second
                right.repeat(dx) + up.repeat(dy) + drop
            }
            (start.first == end.first) &&
                    (start.second < end.second) -> {
                val dx = 0
                val dy = end.second - start.second
                up.repeat(dy) + drop
            }
            (start.first > end.first) &&
                    (start.second < end.second) -> {
                val dx = start.first - end.first
                val dy = end.second - start.second
                left.repeat(dx) + up.repeat(dy) + drop
            }
            (start.first > end.first) &&
                    (start.second == end.second) -> {
                val dx = start.first - end.first
                val dy = 0
                left.repeat(dx) + drop
            }
            else -> if (shouldDropMultiplePizza) drop else ""
        }
    }

    // finds the nearest element to the current element
    private fun findNearest(beginning: Pair<Int, Int>, list: List<Pair<Int, Int>>): Pair<Int, Int>{
        var nearest = list[0]
        var distance = calculateDistance(beginning, list[0])
        list.forEach {
            val newDistance = calculateDistance(beginning, it)
            if (distance > newDistance){
                nearest = it
                distance = newDistance
            }
        }
        return nearest
    }

    // calculates the distance between two dots with the Pythagorean theorem
    private fun calculateDistance(beginning: Pair<Int, Int>, dest: Pair<Int, Int>): Int{
        return sqrt((dest.first.toFloat() - beginning.first.toFloat()).pow(2) +
                (dest.second.toFloat() - beginning.second.toFloat()).pow(2)).toInt()
    }

    // transforms the input string to the list of Pair<Int, Int>
    private fun getPairsFromString(string: String): List<Pair<Int, Int>>{
        val listOfNumbers = string.substring(4).filter {
            try {
                it.toString().toInt()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }.map { it.toString().toInt() }

        val listOfPairs = listOf<Pair<Int, Int>>().toMutableList()

        listOfNumbers.forEachIndexed { index, number ->
            if(index % 2 == 0) {
                listOfPairs.add(number to listOfNumbers[index + 1])
            }
        }
        return listOfPairs
    }

}