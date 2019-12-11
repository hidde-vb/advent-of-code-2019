package net.hiddevb.advent.advent2019.day06

import net.hiddevb.advent.common.initialize
import kotlin.math.floor

/**
 * --- Day 6: Universal Orbit Map ---
 */

private val entry = "COM"

fun main() {
    val fileStrings = initialize("Day 6: Universal Orbit Map", arrayOf("day6.txt"))

    println("Part 1: Basic")
    val solution1 = solveBasic(fileStrings[0])
    println("Solved!\nSolution: $solution1\n")

//    println("Part 2: Advanced")
//    val solution2 = solveAdvanced(fileStrings[0])
//    println("Solved!\nSolution: $solution2\n")
}

// Part 1
fun solveBasic(input: String): Int {
    val orbits = input.split("\n").map { it.split(")") }.map { Orbit(it[0], it[1])}
    val masses = ArrayList<Mass>()

    masses.add(Mass(entry, 0))
    var x = 0
    while(x < masses.size) {

        for (orbit in orbits) {
            if(orbit.center == masses[x].center) {
                masses.add(Mass(orbit.moon, masses[x].distance + 1))

            }
        }
        print("$masses[x],")
        x++
    }

    return masses.map { it.distance }.sum()
}
// Part 2
fun solveAdvanced(input: String): Int {
    return 0
}

data class Orbit(val center: String, val moon: String)

data class Mass(val center: String, val distance: Int)