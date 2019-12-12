package net.hiddevb.advent.advent2019.day08

import net.hiddevb.advent.common.initialize
import java.util.*
import java.util.Collections.min

/**
 * --- Day 8: Space Image Format ---
 */

private const val WIDTH = 25
private const val HEIGHT = 6

fun main() {
    val fileStrings = initialize("Day 8: Space Image Format", arrayOf("day8.txt"))

    println("Part 1: Basic")
    val solution1 = solveBasic(fileStrings[0])
    println("Solved!\nSolution: $solution1\n")

//    println("Part 2: Advanced")
//    val solution2 = solveAdvanced(fileStrings[0])
//    println("Solved!\nSolution: $solution2\n")
}

// Part 1
fun solveBasic(input: String): Int {
    val layer = getLeastcorruptedLayer(input.chunked(WIDTH * HEIGHT))
    return layer.count { "1".contains(it) } * layer.count { "2".contains(it) }
}

private fun getLeastcorruptedLayer(chunks: List<String>): String = chunks.minBy { it.count { l -> "0".contains(l) } }!!

// Part 2
fun solveAdvanced(input: String): Int {
    return 0
}
