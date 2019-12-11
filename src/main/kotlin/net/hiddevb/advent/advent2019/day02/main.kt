package net.hiddevb.advent.advent2019.day02

import net.hiddevb.advent.advent2019.intcodeMachine.getDefaultInstructionList
import net.hiddevb.advent.common.initialize
import kotlin.math.floor

/**
 * --- Day 2: 1202 Program Alarm ---
 *
 * REFACTORING
 * -----------
 *
 *  - Make a seperate class for the computer
 */

const val EXCEPTED_OUTPUT = 19690720

fun main() {
    val fileStrings = initialize("Day 2: 1202 Program Alarm", arrayOf("day2.txt"))

    println("Part 1: Basic")
    val solution1 = solveBasic(fileStrings[0])
    println("Solution: $solution1\n")

    println("Part 2: Advanced")
    val solution2 = solveAdvanced(fileStrings[0])
    println("Solution: $solution2\n")
}

// Part 1
fun solveBasic(input: String): Int {
    var intCodes = input.split(",").map { it.trim().toInt() }.toTypedArray()
    intCodes = setVerbAndNoun(intCodes, 12, 2)

    return runComputer(intCodes)
}

// Part 2
fun solveAdvanced(input: String): Int {
    for (verb in 0..100) {
        for (noun in 0..100) {
            var intCodes = input.split(",").map { it.trim().toInt() }.toTypedArray()
            intCodes = setVerbAndNoun(intCodes, noun, verb)
            if (runComputerWith(intCodes) == EXCEPTED_OUTPUT) {
                println("SOLVED")
                return 100 * noun + verb

            }
        }
    }
    println("WARN: no solution found.")
    return 0
}

fun runComputer(input: Array<Int>): Int {
    var intCodes = input
    var pointer = 0
    val instructions = getDefaultInstructionList()
    while (true) {
        intCodes = instructions
                .find { ins -> ins.optCode == intCodes[pointer] }
                ?.execute(intCodes, pointer)
                ?: return intCodes[0]

        val instruction = instructions.find { ins -> ins.optCode == intCodes[pointer] }
        if (instruction != null) {
            intCodes = instruction.execute(intCodes, pointer)
            pointer = instruction.nextInstruction(pointer)
        } else {
            return intCodes[0]
        }
    }
}

fun runComputerWith(input: Array<Int>): Int {
    var intCodes = input
    var pointer = 0
    while (true) {
        intCodes = when (intCodes[pointer]) {
            1 -> sumInstruction(intCodes, pointer)
            2 -> productInstruction(intCodes, pointer)
            99 -> {
                println("DONE: ${intCodes[0]} for ${intCodes[1]},${intCodes[2]}")
                return intCodes[0]
            }
            else -> {
                println("ERR: unknown instruction $intCodes[pointer] at pointer $pointer")
                return 0
            }
        }

        pointer = pointer.nextInstruction()
    }
}

// Instructions

fun sumInstruction(intCodes: Array<Int>, pointer: Int): Array<Int> {
    intCodes[intCodes[pointer + 3]] = intCodes[intCodes[pointer + 1]] + intCodes[intCodes[pointer + 2]]
    return intCodes
}

fun productInstruction(intCodes: Array<Int>, pointer: Int): Array<Int> {
    intCodes[intCodes[pointer + 3]] = intCodes[intCodes[pointer + 1]] * intCodes[intCodes[pointer + 2]]
    return intCodes
}

fun Int.nextInstruction() = this + 4

fun setVerbAndNoun(intCodes: Array<Int>, noun: Int, verb: Int): Array<Int> {
    intCodes[1] = noun
    intCodes[2] = verb
    return intCodes
}



