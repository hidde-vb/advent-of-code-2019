package net.hiddevb.advent.common

import java.io.File

const val LOCATION = "src/main/resources/input/"

fun initialize(title: String = "Puzzle", fileNames: Array<String>):List<String> {
    println("*oO* $title *Oo*")
    return readFiles(fileNames)
}

fun readFiles(fileNames: Array<String>): List<String> {
    return fileNames.asList()
        .map { name -> LOCATION + name }
        .map { path -> File(path).inputStream().readBytes().toString(Charsets.UTF_8) }
}

