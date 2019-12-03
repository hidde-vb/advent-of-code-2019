package net.hiddevb.advent.advent2019.day03

import net.hiddevb.advent.common.initialize
import kotlin.math.abs

/**
 * --- Day 3: Crossed Wires ---
 */

fun main() {
    val fileStrings = initialize("Day 3: Crossed Wires", arrayOf("day3.txt"))

    println("Part 1: Basic")
    val solution1 = solveBasic(fileStrings[0])
    println("Solved!\nSolution: $solution1\n")

    println("Part 2: Advanced")
    val solution2 = solveAdvanced(fileStrings[0])
    println("Solved!\nSolution: $solution2\n")
}

// Part 1
fun solveBasic(input: String): Int? {
    val wireStrings = input.split("\n")
    val piecesOne = generatePieceSet(wireStrings[0].split(","))
    val piecesTwo = generatePieceSet(wireStrings[1].split(","))

    val crossings: MutableList<Int> = ArrayList()
    for(pieceI in piecesTwo) {
        for(pieceJ in piecesOne) {
            val point: Point? = pieceI.getCrossingPoint(pieceJ)
            if(point != null && point != Point(0,0, 0)) {
                crossings.add(abs(point.x) + abs(point.y))
            }

        }
    }

    return crossings.min()
}

// Part 2

fun solveAdvanced(input: String): Int? {
    val wireStrings = input.split("\n")
    val piecesOne = generatePieceSet(wireStrings[0].split(","))
    val piecesTwo = generatePieceSet(wireStrings[1].split(","))

    val crossings: MutableList<Int> = ArrayList()
    for(pieceI in piecesTwo) {
        for(pieceJ in piecesOne) {
            val point: Point? = pieceI.getCrossingPoint(pieceJ)
            if(point != null && point != Point(0,0, 0)) {
                crossings.add(point.dist)
            }

        }
    }
    println(crossings)

    return crossings.min()
}

fun generatePieceSet(wire: List<String>): MutableList<Piece> {
    val toReturn: MutableList<Piece> = ArrayList()

    var x = 0
    var y = 0
    var dist = 0
    for (instruction in wire) {
        val direction = instruction[0]
        val amount = instruction.substring(1).toInt()
        when (direction) {
            'R' -> {
                toReturn.add(Piece(Point(x, y, dist), Point(x + amount, y, dist)))
                x += amount
            }
            'L' -> {
                toReturn.add(Piece(Point(x - amount, y, dist), Point(x, y, dist)))
                x -= amount
            }
            'U' -> {
                toReturn.add(Piece(Point(x, y, dist), Point(x, y + amount,dist)))
                y += amount
            }
            'D' -> {
                toReturn.add(Piece(Point(x, y - amount,dist), Point(x, y,dist)))
                y -= amount
            }
        }
        dist += amount
    }
    return toReturn
}