package net.hiddevb.advent.advent2019.day05

import net.hiddevb.advent.common.initialize
import kotlin.math.floor

/**
 * --- Day 5: Sunny with a Chance of Asteroids ---
 */

fun main() {
    val fileStrings = initialize("Day 5: Sunny with a Chance of Asteroids", arrayOf("day5.txt"))

    println("Part 1: Basic")
    val solution1 = solveBasic(fileStrings[0])
    println("Solved!\nSolution: $solution1\n")

    println("Part 2: Advanced")
    val solution2 = solveAdvanced(fileStrings[0])
    println("Solved!\nSolution: $solution2\n")
}

// Part 1
fun solveBasic(input: String): Double {
    val masses = input.split("\n")

    var totalRequiredFuel = 0.0
    for (mass in masses) {
        if (mass.isNotEmpty()) {
            totalRequiredFuel += calculateFuelBasic(mass.toDouble())
        }
    }

    return totalRequiredFuel
}

fun calculateFuelBasic(mass: Double): Double {
   return floor(mass / 3) - 2
}

// Part 2
fun solveAdvanced(input: String): Double {
    val masses = input.split("\n")

    var totalRequiredFuel = 0.0
    for (mass in masses) {
        if (mass.isNotEmpty()) {
            totalRequiredFuel += calculateFuelAdvanced(mass.toDouble())
        }
    }

    return totalRequiredFuel
}

fun calculateFuelAdvanced(mass: Double, acc: Double = 0.0): Double {
     val additionalMass = floor(mass / 3) - 2
     return when {
         additionalMass > 0 -> calculateFuelAdvanced(additionalMass, acc + additionalMass)
         else -> return acc
     }
}