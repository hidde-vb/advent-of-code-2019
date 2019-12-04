package net.hiddevb.advent.advent2019.day04

import net.hiddevb.advent.common.initialize
import kotlin.math.floor

/**
 * --- Day 4: Secure Container ---
 */

const val INPUT_START = 158126
const val INPUT_END = 624574

fun main() {
    initialize("Day 4: Secure Container", arrayOf())

    println("Part 1: Basic")
    val solution1 = solveBasic()
    println("Solved!\nSolution: $solution1\n")

//    println("Part 2: Advanced")
//    val solution2 = solveAdvanced()
//    println("Solved!\nSolution: $solution2\n")

    print(isValidCode(111122))
}

// Part 1
fun solveBasic(): Int {
    var total = 0
    for (code in INPUT_START..INPUT_END) {
        if (isValidCode(code)) {
            println("SUCCES: $code")
            total++
        }
    }

    return total
}

fun isValidCode(code: Int): Boolean {
    val codeArray = code.toString().map { it.toString().toInt() }
    val limit = codeArray.size - 1

    var hasDouble = false
    for (i in 0 until limit) {
        if (codeArray[i] > codeArray[i + 1]) {
            return false
        }
        if (codeArray[i] == codeArray[i + 1]) {
            if (!((i - 1 >= 0 && codeArray[i - 1] == codeArray[i])
                    || (i + 2 <= limit && codeArray[i + 2] == codeArray[i]))) {
                hasDouble = true
            }
        }
    }
    return hasDouble
}


// Part 2
fun solveAdvanced(): Int {

    return 0
}