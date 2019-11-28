package net.hiddevb.advent.advent2019.dayZero

import net.hiddevb.advent.common.initialize

/**
 * --- Day 0: Not Quite Lisp ---
 *
 * The very first from 2015 challenge to test the setup
 */
const val UP_CHARACTER = "("
const val DOWN_CHARACTER = ")"

fun main() {
    val fileStrings = initialize("Day 0: Not Quite Lisp", arrayOf("dayZero.txt"))

    val solution = solve(fileStrings[0])

    println("Solved!\n\nSolution: $solution")
}

fun solve(input: String): Int {
    val inputChars = input.split("")

    var floor = 0
    for (symbol in inputChars) {
        when (symbol) {
            UP_CHARACTER -> floor++
            DOWN_CHARACTER -> floor--
            else -> println("warn: symbol \"$symbol\" not found")
        }
    }

    return floor
}