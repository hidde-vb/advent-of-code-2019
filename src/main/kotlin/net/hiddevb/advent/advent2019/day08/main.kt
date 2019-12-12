package net.hiddevb.advent.advent2019.day08

import net.hiddevb.advent.common.initialize

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

    println("Part 2: Advanced")
    val solution2 = solveAdvanced(fileStrings[0])
    println("Solved!\nSolution:\n$solution2\n")
}

// Part 1
fun solveBasic(input: String): Int {
    val layer = getLeastcorruptedLayer(input.chunked(WIDTH * HEIGHT))
    return layer.count { "1".contains(it) } * layer.count { "2".contains(it) }
}

private fun getLeastcorruptedLayer(chunks: List<String>): String = chunks.minBy { it.count { l -> "0".contains(l) } }!!

// Part 2
fun solveAdvanced(input: String): String {
    val layers = input.chunked(WIDTH * HEIGHT)
    val mergedLayer = layers.last().toCharArray()

    for (l in layers.size - 1 downTo 0) {
        for (c in 0 until mergedLayer.size) {
            when (layers[l][c]) {
                '0' -> mergedLayer[c] = '0'
                '1' -> mergedLayer[c] = '1'
            }
        }
    }
    return render(mergedLayer)
}

private fun render(layer: CharArray): String {
    var render = ""
    for(i in 0 until layer.size) {
        when (layer[i]) {
            '0' -> render = "$render "
            '1' -> render = "$render#"
            '2' -> render = "$render "
        }
        if ((1+i) % WIDTH == 0) {
            render = "$render\n"
        }
    }
    return render
}

